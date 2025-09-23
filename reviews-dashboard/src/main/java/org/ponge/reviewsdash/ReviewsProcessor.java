package org.ponge.reviewsdash;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class ReviewsProcessor {

    @Incoming("incoming-reviews")
    @Broadcast
    @Outgoing("dispatched-reviews")
    @RunOnVirtualThread
    public JsonObject process(JsonObject review) {
        Log.info("Received: " + review.encode());
        return review;
    }

    @Incoming("dispatched-reviews")
    @Broadcast
    @Outgoing("bad-reviews")
    public Multi<JsonObject> detectBadReviews(Multi<JsonObject> stream) {
        return stream
                .select()
                .where(review -> review.getInteger("rating") <= 1)
                .invoke(review -> Log.info("🚨 Spotted a bad review: " + review.encode()));

    }
}
