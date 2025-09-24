package me.escoffier.todo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class TodoResourceTest {

    @Test
    @Order(1)
    public void verifyICanAccessMyTodoList() {
        given()
                .get("/api")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(4));

        given()
                .get("/api/{id}", 1000)
                .then()
                .statusCode(404);
    }

    @Test
    @Order(2)
    public void testThatICanChangeMyTodoList() {
        Todo todo = new Todo();
        todo.setTitle("test");
        todo.setCompleted(false);

        given()
                .get("/api")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(4));

        String id = given()
                .header("content-type", "application/json")
                .body(todo)
                .post("/api")
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getString("id");

        given()
                .get("/api")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(5));

        given()
                .get("/api/{id}", id)
                .then()
                .statusCode(200)
                .body("title", is("test"))
                .body("completed", is(false));

        todo.setTitle("fixed");
        todo.setCompleted(true);
        given()
                .header("content-type", "application/json")
                .body(todo)
                .patch("/api/{id}", id)
                .then()
                .statusCode(200);

        given()
                .get("/api/{id}", id)
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", is("fixed"))
                .body("completed", is(true));

        given()
                .delete("/api/{id}", id)
                .then()
                .statusCode(204);

        given()
                .get("/api")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(4));
    }


}