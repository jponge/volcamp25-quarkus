package me.escoffier.todo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
class TodoResourceTest {

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );


    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

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