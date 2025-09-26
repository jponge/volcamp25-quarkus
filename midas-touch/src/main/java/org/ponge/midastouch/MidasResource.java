package org.ponge.midastouch;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;
import org.ponge.midastouch.data.Product;
import org.ponge.midastouch.data.Review;
import org.ponge.midastouch.services.BackendService;
import org.ponge.midastouch.services.SummaryService;

import java.util.List;

@Path("/")
public class MidasResource {

    @RestClient
    BackendService backendService;

    @Inject
    SummaryService summaryService;

    private Product findProduct(String name) {
        Product product = backendService.products().stream()
                .filter(p -> p.name().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product not found: " + name));
        return product;
    }

    @GET
    @Path("/all-reviews/{name}")
    public List<Review> allReviews(String name) {
        Product product = findProduct(name);
        return backendService.reviews(product.id());
    }

    @GET
    @Path("/reviews")
    public String reviews(
            @RestQuery String name,
            @RestQuery("avg") @DefaultValue("false") boolean includeAverage) {

        Product product = findProduct(name);

        List<Review> reviews = backendService.reviews(product.id());
        if (reviews.isEmpty()) {
            return "No reviews found for " + name;
        }

        if (includeAverage) {
            Log.info("Summarizing reviews with average rating for: " + product.name());
            return summaryService.summarizeWithAverage(product, reviews);
        } else {
            Log.info("Summarizing reviews for: " + product.name());
            return summaryService.summarize(product, reviews);
        }
    }

    @GET
    @Path("/product-names")
    public List<String> productNames() {
        return backendService.products().stream()
                .map(Product::name)
                .toList();
    }
}
