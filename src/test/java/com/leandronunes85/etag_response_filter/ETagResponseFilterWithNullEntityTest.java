package com.leandronunes85.etag_response_filter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ETagResponseFilterWithNullEntityTest extends ETagResponseFilterHashingEntityAbstractTest {

    @Override
    protected Object getEntity() {
        return null;
    }

    @Test
    public void doesNotGenerateETag() throws Exception {
        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaders()).doesNotContainKey("ETag");
    }

    @Test
    public void doesNotChangeResponseContext() throws Exception {
        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getStatusInfo()).isEqualTo(INITIAL_STATUS);
        assertThat(responseContext.getHeaders()).containsKey(INITIAL_HEADER_NAME);
        assertThat(responseContext.getHeaders().getFirst(INITIAL_HEADER_NAME)).isEqualTo(INITIAL_HEADER_VALUE);
        assertThat(responseContext.getEntity()).isEqualTo(getEntity());
    }
}
