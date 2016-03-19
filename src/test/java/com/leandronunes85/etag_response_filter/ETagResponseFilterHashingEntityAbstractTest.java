package com.leandronunes85.etag_response_filter;

import com.leandronunes85.etag_response_filter.stub.ContainerResponseContextStub;
import org.junit.Before;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class ETagResponseFilterHashingEntityAbstractTest extends ETagResponseFilterAbstractTest {

    protected static final String EXPECTED_ENTITY_REPRESENTATION = "ExpectedRepresentation";
    protected static final String CALCULATED_ETAG = "CalculatedETag";

    @Before
    public void setUpVictimWithHashFunction() {
        Function<String, String> hashFunction = mock(Function.class);
        victim = new ETagResponseFilter(hashFunction);
        when(hashFunction.apply(EXPECTED_ENTITY_REPRESENTATION)).thenReturn(CALCULATED_ETAG);
    }

    @Before
    @Override
    public void setUpResponseContext() {
        responseContext = new ContainerResponseContextStub(
                INITIAL_STATUS, INITIAL_HEADER_NAME, INITIAL_HEADER_VALUE, getEntity());
    }

    @Override
    protected Request getRequest() {
        Request mockRequest = mock(Request.class);

        when(mockRequest.evaluatePreconditions(any(EntityTag.class)))
                .thenReturn(null);

        return mockRequest;

    }

    protected abstract Object getEntity();
}
