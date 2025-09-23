package org.ponge.reviewsdash;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.reactive.RestStreamElementType;

@Path("/")
public class DashboardResource {

    @Channel("dispatched-reviews")
    Multi<JsonObject> dispatchedReviews;

    @Channel("bad-reviews")
    Multi<JsonObject> badReviews;

    @GET
    @Path("/live")
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<JsonObject> liveUpdates() {
        return dispatchedReviews;
    }

    @GET
    @Path("/bad-reviews")
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<JsonObject> badReviews() {
        return badReviews;
    }

}
