package com.leandronunes85.etag.responsefilter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ETagResponseFilterForNonChangingResponseContextsAbstractTest extends ETagResponseFilterAbstractTest {

    @Test
    public void doesNotUpdateResponseContextStatus() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getStatusInfo()).isEqualTo(INITIAL_STATUS);
    }

    @Test
    public void doesNotUpdateResponseHeaders() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaders()).containsKey(INITIAL_HEADER_NAME);
        assertThat(responseContext.getHeaders().get(INITIAL_HEADER_NAME)).contains(INITIAL_HEADER_VALUE);
    }

    @Test
    public void doesNotUpdateResponseEntity() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getEntity()).isEqualTo(INITIAL_ENTITY);
    }

    @Test
    public void addsETagHeaderToResponse() throws Exception {

        assertThat(responseContext.getHeaders()).doesNotContainKey("ETag");

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaders()).containsKey("ETag");
        assertThat(responseContext.getHeaderString("ETag")).isNotEmpty();
    }
}
