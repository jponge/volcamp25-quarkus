package org.ponge.ratingapp;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EndpointsTest {

    @Test
    void test_some_crud() {
        JsonObject tires = new JsonObject()
                .put("name", "CrossClimate")
                .put("description", "An all-season tire.");

        int id = given()
                .body(tires.encode())
                .contentType(ContentType.JSON)
                .when().post("/products")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when().get("/products/" + id)
                .then()
                .statusCode(200)
                .body("name", is("CrossClimate"))
                .body("description", is("An all-season tire."));

        given()
                .when().get("/products/find/CrossClimate")
                .then()
                .statusCode(200)
                .body("name", is("CrossClimate"))
                .body("description", is("An all-season tire."))
                .body("id", is(id));

        JsonObject goodRating = new JsonObject()
                .put("productId", id)
                .put("rating", 5)
                .put("comment", "Excellent in the winter, good in the summer");
        JsonObject badRating = new JsonObject()
                .put("productId", id)
                .put("rating", 1)
                .put("comment", "Noisy, and I managed to cook them while drifting in hot summer.");

        int goodId = given()
                .body(goodRating.encode())
                .contentType(ContentType.JSON)
                .when().post("/reviews")
                .then()
                .statusCode(201)
                .extract().path("id");

        int badId = given()
                .body(badRating.encode())
                .contentType(ContentType.JSON)
                .when().post("/reviews")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when().get("/reviews/average/" + id)
                .then()
                .statusCode(200)
                .body("rating", is(3.0f));

        given()
                .when().get("/reviews/of/" + id)
                .then()
                .statusCode(200)
                        .body("id", hasItems(goodId, badId))
                        .body("rating", hasItems(5, 1));

        given()
                .when().get("/reviews/of/" + badId)
                .then()
                .statusCode(200);
    }
}
