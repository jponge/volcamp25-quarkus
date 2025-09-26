package org.ponge.midastouch.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.ponge.midastouch.data.Average;
import org.ponge.midastouch.data.Product;
import org.ponge.midastouch.data.Review;

import java.util.List;

@RegisterRestClient(configKey = "backend-api")
public interface BackendService {

    @GET
    @Path("/products")
    List<Product> products();

    @GET
    @Path("/reviews/of/{id}")
    List<Review> reviews(long id);

    @GET
    @Path("/reviews/average/{id}")
    Average averageReviews(long id);
}
