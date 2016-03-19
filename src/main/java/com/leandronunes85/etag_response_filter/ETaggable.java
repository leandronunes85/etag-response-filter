package com.leandronunes85.etag_response_filter;

/**
 * Interface to be implemented by Entities that want to specify explicitly which field(s) to use for the ETag generation
 * process instead of relying on {@link Object#toString()} method.
 *
 * @see ETagResponseFilter
 */
public interface ETaggable {
    String getEntityRepresentation();
}
