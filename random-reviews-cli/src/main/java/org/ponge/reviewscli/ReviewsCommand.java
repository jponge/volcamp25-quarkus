package org.ponge.reviewscli;

import io.quarkus.runtime.Quarkus;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import io.vertx.mutiny.ext.web.codec.BodyCodec;
import net.datafaker.Faker;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@CommandLine.Command(
        mixinStandardHelpOptions = true,
        description = "Reviews generator"
)
public class ReviewsCommand implements Runnable {

    @CommandLine.Option(
            names = "--host",
            description = "Host of the target backend",
            required = true,
            defaultValue = "localhost")
    String host;

    @CommandLine.Option(
            names = "--port",
            description = "Port of the target backend",
            required = true,
            defaultValue = "8080")
    int port;

    @CommandLine.Option(
            names = "--period",
            description = "Update period (in ms)",
            required = true,
            defaultValue = "1000")
    int period;

    Vertx vertx;
    WebClient webClient;

    List<Product> products = new ArrayList<>();

    @Override
    public void run() {
        setup();
    }

    private void setup() {
        vertx = Vertx.vertx();
        webClient = WebClient.create(vertx);

        System.out.println("🎯 Target host [" + host + "] on port [" + port + "], period [" + period + "]ms");

        Faker faker = new Faker();
        List<String> generatedProducts = faker.stream(
                        () -> faker.appliance().brand() + " - " + faker.appliance().equipment()
                )
                .minLen(4)
                .maxLen(6)
                .<Stream<String>>generate()
                .toList();

        generatedProducts.forEach(name -> {
            String description = faker.lorem().sentence();
            JsonObject product = new JsonObject()
                    .put("name", name)
                    .put("description", description);
            HttpResponse<JsonObject> response = webClient.post(port, host, "/products")
                    .as(BodyCodec.jsonObject())
                    .sendJsonObjectAndAwait(product);
            if (response.statusCode() == 404) {
                return;
            }
            JsonObject body = response.body();
            Product newProduct = new Product(
                    body.getString("id"),
                    body.getString("name"),
                    body.getString("description")
            );
            products.add(newProduct);
            System.out.println("⚡️ New product added: " + newProduct);
        });

        Random random = new Random();
        vertx.setPeriodic(period, tick -> {
            Product product = products.get(random.nextInt(0, products.size()));
            String comment = faker.chuckNorris().fact();
            int rating = random.nextInt(0, 6);
            JsonObject review = new JsonObject()
                    .put("productId", product.id)
                    .put("rating", rating)
                    .put("comment", comment);
            webClient.post(port, host, "/reviews")
                    .as(BodyCodec.jsonObject())
                    .sendJsonObject(review)
                    .subscribe().with(response -> {
                        if (response.statusCode() == 201) {
                            System.out.println("✨ " + product.name + " rated " + rating + ": " + comment);
                        } else {
                            System.out.println("🚨 Failed to post a review: status code " + response.statusCode());
                        }
                    }, err -> System.out.println("🚨 Failed to post a review: " + err.getMessage()));

        });

        Quarkus.waitForExit();
    }

    record Product(String id, String name, String description) {
    }
}
