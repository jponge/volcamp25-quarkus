package org.ponge.ratingapp;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EndpointsTest {

    @Test
    void testSomethingElse() {
        Log.info("Is 1 == 1?");
        Assertions.assertTrue(1 == 1);
    }

    @Test
    void test_hello() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body("name", is("Volcamp"))
                .body("message", is("Hello Volcamp!"));
    }
}
