package com.leandronunes85.etag_response_filter;

import org.junit.Test;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ETagResponseFilterForInvalidIfNoneMatchHeaderTest extends ETagResponseFilterForNonChangingResponseContextsAbstractTest {

    @Override
    protected Request getRequest() {
        Request mockRequest = mock(Request.class);

        when(mockRequest.evaluatePreconditions(any(EntityTag.class)))
                .thenThrow(RuntimeException.class);

        return mockRequest;

    }

    @Test
    public void addsWarningHeaderToResponse() throws Exception {

        assertThat(responseContext.getHeaders()).doesNotContainKey("Warning");

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaders()).containsKey("Warning");
        assertThat(responseContext.getHeaderString("Warning")).isNotEmpty();
    }

}
