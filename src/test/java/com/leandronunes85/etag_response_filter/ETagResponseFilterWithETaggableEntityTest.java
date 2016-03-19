package com.leandronunes85.etag_response_filter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ETagResponseFilterWithETaggableEntityTest extends ETagResponseFilterHashingEntityAbstractTest {

    @Override
    protected Object getEntity() {
        return new ETaggable() {
            @Override
            public String getEntityRepresentation() {
                return EXPECTED_ENTITY_REPRESENTATION;
            }

            @Override
            public String toString() {
                return "UnexpectedRepresentation";
            }
        };
    }

    @Test
    public void usesHashFunctionOnEntityGetEntityRepresentation() throws Exception {
        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaderString("ETag")).contains(CALCULATED_ETAG);
    }
}
