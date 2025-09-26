package org.ponge.midastouch.services;

import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MidasToolbox {

    @RestClient
    BackendService backendService;

    @Tool("The average rating for a product")
    public double average_rating(long id) {
        double rating = backendService.averageReviews(id).rating();
        Log.info("✨ ✨ ✨ Average rating for: " + id + " is " + rating);
        return rating;
    }
}
