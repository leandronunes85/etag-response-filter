# ETag Response Filter

This project contains a ResponseFilter implementation that handles both ETag creation and If-None-Match header checking.

## Usage

For using it you simply need to register this new ContainerResponseFilter as part of your service. In Dropwizard, for instance,
it would look something like:

```java
import com.leandronunes85.etag_response_filter.ETagResponseFilter;

...

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        ...
        environment.jersey().getResourceConfig().register(new ETagResponseFilter());
        ...
    }

...
```

Then you just have to annotate whichever resource(s) you want with the @ETag annotation in order for it to be used:

```java
import com.leandronunes85.etag_response_filter.ETag;

...

@Path("people")
public class PersonResource {
    
    ...

    @GET
    @Path("{id}")
    @ETag
    public Person getPerson(@PathParam("id") String id) {
        return ...;
    }

    ...
    
}
```

Now if you inspect the responses to this endpoint, you'll notice a new ETag header. This header is the result of hashing 
either the result of Person's #getEntityRepresentation() method (if Person implements ETaggable) or #toString() method 
otherwise. You should always try to use the ETaggable interface because a) it makes it explicit which fields are going to
be used for ETag calculation, and b) most times it would be more efficient to call this method over the toString() (most 
likely your entity relies on a limited set of properties for versioning - lastModifiedDate?)
