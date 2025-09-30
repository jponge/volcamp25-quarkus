package org.ponge.ratingapp.endpoints;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyResource {

    record Hello(
            String name,
            String message,
            long timestamp) {}

    @GET
    @Path("/hello")
    public Hello getHello() {
        return new Hello("Volcamp", "Hello Volcamp!", System.currentTimeMillis());
    }
}
