package com.leandronunes85.etag.responsefilter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ETagResponseFilterWithNonETaggableEntityTest extends ETagResponseFilterHashingEntityAbstractTest {

    @Override
    protected Object getEntity() {
        return new Object() {
            @Override
            public String toString() {
                return EXPECTED_ENTITY_REPRESENTATION;
            }
        };
    }

    @Test
    public void usesHashFunctionOnEntityToString() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaderString("ETag")).contains(CALCULATED_ETAG);
    }

}
