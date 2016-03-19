package com.leandronunes85.etag_response_filter;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ETagResponseFilterForSameEntityVersionTest extends ETagResponseFilterForNonChangingResponseContextsAbstractTest {

    @Override
    protected Request getRequest() {
        Request mockRequest = mock(Request.class);

        when(mockRequest.evaluatePreconditions(any(EntityTag.class)))
                .thenReturn(null);

        return mockRequest;

    }
}
