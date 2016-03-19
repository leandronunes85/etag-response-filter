package com.leandronunes85.etag_response_filter;

import com.leandronunes85.etag_response_filter.stub.ContainerResponseContextStub;
import org.junit.Before;
import org.mockito.Mock;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response.Status;

import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;


public abstract class ETagResponseFilterAbstractTest {

    protected static final Status INITIAL_STATUS = Status.OK;
    protected static final String INITIAL_HEADER_NAME = "HeaderName";
    protected static final String INITIAL_HEADER_VALUE = "HeaderValue";
    protected static final Object INITIAL_ENTITY = "Entity";

    protected ETagResponseFilter victim = new ETagResponseFilter();
    @Mock
    protected ContainerRequestContext requestContext;

    protected ContainerResponseContext responseContext;

    @Before
    public void setUpMocks() {
        initMocks(this);
        doReturn(getRequest()).when(requestContext).getRequest();
    }

    @Before
    public void setUpResponseContext() {
        responseContext = new ContainerResponseContextStub(
                INITIAL_STATUS, INITIAL_HEADER_NAME, INITIAL_HEADER_VALUE, INITIAL_ENTITY);
    }

    protected abstract Request getRequest();
}