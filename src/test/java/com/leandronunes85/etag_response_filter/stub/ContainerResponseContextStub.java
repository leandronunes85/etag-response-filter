package com.leandronunes85.etag_response_filter.stub;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.*;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ContainerResponseContextStub implements ContainerResponseContext {

    private Response.StatusType statusType;
    private final MultivaluedMap<String, Object> headers;
    private Object entity;

    public ContainerResponseContextStub(Response.StatusType initialStatusType,
                                        String initialHeaderName,
                                        Object initialHeaderValue,
                                        Object initialEntity) {
        this.statusType = initialStatusType;
        this.headers = new MultivaluedHashMap<>();
        this.headers.add(initialHeaderName, initialHeaderValue);
        this.entity = initialEntity;
    }

    @Override
    public int getStatus() {
        return statusType.getStatusCode();
    }

    @Override
    public void setStatus(int code) {
        this.statusType = Response.Status.fromStatusCode(code);
    }

    @Override
    public Response.StatusType getStatusInfo() {
        return statusType;
    }

    @Override
    public void setStatusInfo(Response.StatusType statusInfo) {
        this.statusType = statusInfo;
    }

    @Override
    public MultivaluedMap<String, Object> getHeaders() {
        return headers;
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders() {
        throw new NotImplementedException();
    }

    @Override
    public String getHeaderString(String name) {
        return headers.getFirst(name).toString();
    }

    @Override
    public Set<String> getAllowedMethods() {
        throw new NotImplementedException();
    }

    @Override
    public Date getDate() {
        throw new NotImplementedException();
    }

    @Override
    public Locale getLanguage() {
        throw new NotImplementedException();
    }

    @Override
    public int getLength() {
        throw new NotImplementedException();
    }

    @Override
    public MediaType getMediaType() {
        throw new NotImplementedException();
    }

    @Override
    public Map<String, NewCookie> getCookies() {
        throw new NotImplementedException();
    }

    @Override
    public EntityTag getEntityTag() {
        throw new NotImplementedException();
    }

    @Override
    public Date getLastModified() {
        throw new NotImplementedException();
    }

    @Override
    public URI getLocation() {
        throw new NotImplementedException();
    }

    @Override
    public Set<Link> getLinks() {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLink(String relation) {
        throw new NotImplementedException();
    }

    @Override
    public Link getLink(String relation) {
        throw new NotImplementedException();
    }

    @Override
    public Link.Builder getLinkBuilder(String relation) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasEntity() {
        throw new NotImplementedException();
    }

    @Override
    public Object getEntity() {
        return entity;
    }

    @Override
    public Class<?> getEntityClass() {
        return entity.getClass();
    }

    @Override
    public Type getEntityType() {
        return entity.getClass();
    }

    @Override
    public void setEntity(Object entity) {
        this.entity = entity;
    }

    @Override
    public void setEntity(Object entity, Annotation[] annotations, MediaType mediaType) {
        throw new NotImplementedException();
    }

    @Override
    public Annotation[] getEntityAnnotations() {
        throw new NotImplementedException();
    }

    @Override
    public OutputStream getEntityStream() {
        throw new NotImplementedException();
    }

    @Override
    public void setEntityStream(OutputStream outputStream) {
        throw new NotImplementedException();
    }
}
