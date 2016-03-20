package com.leandronunes85.etag_response_filter;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ETagResponseFilterWithCollectionEntityTest extends ETagResponseFilterHashingEntityAbstractTest {

    @Override
    protected List<ETaggable> getEntity() {
        return Arrays.asList(() -> "Entity1", () -> "Entity2", () -> "Entity3", null);
    }

    @Override
    protected String getExpectedEntityRepresentation() {
        return "Entity1Entity2Entity3";
    }

    @Test
    public void usesHashFunctionOnEachGetEntityRepresentation() throws Exception {

        victim.filter(requestContext, responseContext);

        assertThat(responseContext.getHeaderString("ETag")).contains(CALCULATED_ETAG);
    }

}
