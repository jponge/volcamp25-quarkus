package org.ponge.reviewsdash;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.spi.Connector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.awaitility.Awaitility.await;

@QuarkusTest
@QuarkusTestResource(InMemoryChannelResourceLifecycleManager.class)
class ReviewsProcessorTest {

    @Connector("smallrye-in-memory")
    InMemoryConnector connector;

    JsonObject makeReview(long reviewId, long productId, String productName, String productDescription, int rating, String comment) {
        return new JsonObject()
                .put("reviewId", reviewId)
                .put("productId", productId)
                .put("productName", productName)
                .put("productDescription", productDescription)
                .put("rating", rating)
                .put("comment", comment);
    }

    @BeforeEach
    void reset() {
        connector.sink("dispatched-reviews").clear();
        connector.sink("bad-reviews").clear();
    }

    @Test
    void testReviewsDispatching() {
        JsonObject r1 = makeReview(1, 1, "Foo", "Bar", 4, "Nice product");
        JsonObject r2 = makeReview(2, 1, "Foo", "Bar", 1, "Bad product");

        InMemorySource<JsonObject> incomingSource = connector.source("incoming-reviews");
        InMemorySink<JsonObject> dispatchedSink = connector.sink("dispatched-reviews");

        incomingSource.send(r1);
        incomingSource.send(r2);

        await()
                .atMost(Duration.ofSeconds(5))
                .until(dispatchedSink::received, l -> l.size() >= 2);

        assertThatList(dispatchedSink.received())
                .hasSize(2)
                .anyMatch(m -> m.getPayload().getInteger("rating") == 4)
                .anyMatch(m -> m.getPayload().getInteger("rating") == 1);
    }

    @Test
    void testBadReviewsDispatching() {
        JsonObject r1 = makeReview(1, 1, "Foo", "Bar", 4, "Nice product");
        JsonObject r2 = makeReview(2, 1, "Foo", "Bar", 1, "Bad product");

        InMemorySource<JsonObject> incomingSource = connector.source("incoming-reviews");
        InMemorySink<JsonObject> badReviewsSink = connector.sink("bad-reviews");

        incomingSource.send(r1);
        incomingSource.send(r2);

        await()
                .atMost(Duration.ofSeconds(5))
                .until(badReviewsSink::received, l -> !l.isEmpty());

        assertThatList(badReviewsSink.received())
                .hasSize(1)
                .first()
                .matches(m -> m.getPayload().getInteger("rating") == 1);
    }
}
