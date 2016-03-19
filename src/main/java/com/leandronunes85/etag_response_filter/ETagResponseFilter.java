package com.leandronunes85.etag_response_filter;

import org.apache.commons.codec.digest.DigestUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * {@link ContainerResponseFilter} implementation that handles ETag generation as well as If-None-Match request
 * Header. This filter will erase the contents of the generated response (headers and payload) if the generated ETag value
 * and the provided If-None-Match request Header matches each other. In such cases the status code will also be updated
 * to 304 (Not Modified).
 * <p/>
 * ETag will be calculated based on the {@link Object#toString()} method of the response entity unless it implements
 * {@link ETaggable} where the {@link ETaggable#getEntityRepresentation()} method will be used instead.
 *
 * @see <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.19">ETag Header Field Definition</a>
 * @see <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.26">If-None-Match Header Field Definition</a>
 */
@ETag
public class ETagResponseFilter implements ContainerResponseFilter {

    private final Function<String, String> hashFunction;

    public ETagResponseFilter() {
        this(DigestUtils::md5Hex);
    }

    // for testing
    ETagResponseFilter(Function<String, String> hashFunction) {
        this.hashFunction = hashFunction;
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        Optional<EntityTag> calculatedEntityTag = calculateETag(responseContext);

        if (calculatedEntityTag.isPresent()) {
            EntityTag entityTag = calculatedEntityTag.get();
            responseContext.getHeaders().add("ETag", entityTag.toString());

            try {
                Response.ResponseBuilder responseBuilder = requestContext.getRequest().evaluatePreconditions(entityTag);
                if (responseBuilder != null) {
                    updateResponseContextContents(responseContext, responseBuilder);
                }
            } catch (RuntimeException ex) {
                responseContext.getHeaders().add("Warning", format("199 - \"%s\"", ex.getMessage()));
            }
        }
    }

    private Optional<EntityTag> calculateETag(ContainerResponseContext responseContext) throws IOException {

        if (responseContext.getEntity() == null) {
            return Optional.empty();
        }

        String representation = responseContext.getEntity() instanceof ETaggable ?
                ((ETaggable) responseContext.getEntity()).getEntityRepresentation() :
                responseContext.getEntity().toString();

        return Optional.of(new EntityTag(this.hashFunction.apply(representation), true));
    }

    private static void updateResponseContextContents(ContainerResponseContext responseContext, Response.ResponseBuilder responseBuilder) {
        Response newResponse = responseBuilder.build();

        responseContext.setStatusInfo(newResponse.getStatusInfo());
        responseContext.getHeaders().clear();
        newResponse.getHeaders().forEach((key, valueList) -> responseContext.getHeaders().addAll(key, valueList));
        responseContext.setEntity(newResponse.getEntity());
    }


}
