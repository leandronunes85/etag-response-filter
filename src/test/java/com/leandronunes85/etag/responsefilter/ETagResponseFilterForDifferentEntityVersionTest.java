package com.leandronunes85.etag.responsefilter;

import org.junit.Test;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ETagResponseFilterForDifferentEntityVersionTest extends ETagResponseFilterAbstractTest {

    @Override
    protected Request getRequest() {
        Request mockRequest = mock(Request.class);

        when(mockRequest.evaluatePreconditions(any(EntityTag.class)))
                .thenAnswer(invocation -> {
                    EntityTag entityTag = invocation.getArgumentAt(0, EntityTag.class);
                    return Response.notModified().tag(entityTag);
                });

        return mockRequest;
    }

    @Test
    public void updatesResponseContextStatus() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getStatusInfo()).isEqualTo(Response.Status.NOT_MODIFIED);
    }

    @Test
    public void updatesResponseHeaders() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaders().keySet()).containsOnly("ETag");
    }

    @Test
    public void updatesResponseEntity() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getEntity()).isNull();
    }
}
