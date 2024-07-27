package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.postoffice.api.config.Config;
import org.postoffice.api.requestSpecification.JsonPlaceHolderRequestSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonPlaceholderApiTest {
    static RequestSpecification requestSpecs;
    private static final Logger logger = (Logger) LogManager.getLogger(JsonPlaceholderApiTest.class);

    @BeforeAll
    public void setup() {
        requestSpecs = JsonPlaceHolderRequestSpecification.getRequestSpecification();
        if (requestSpecs == null) {
            throw new RuntimeException("Request Spec is null");
        }
        RestAssured.requestSpecification = requestSpecs;
    }

    @Test
    public void testCreateResource() throws InterruptedException {
        logger.info("Create a resource using POST method on - " + Config.Input.BASE_URI.getValue() + Config.Input.ENDPOINT.getValue());
        Response response = given()
                .body(JsonPlaceHolderRequestSpecification.posts("Mr","QA Champions",1).toString())
                .when()
                .post(Config.Input.ENDPOINT.getValue())
                .then()
                .extract().response();
        logger.info("Received response body as \n" + response.asPrettyString());
        Assertions.assertEquals(response.statusCode(), 201);
        Assertions.assertEquals(response.jsonPath().get("title"), "Mr");
        Assertions.assertEquals(response.jsonPath().get("body"), "QA Champions");
        Assertions.assertEquals((Integer) response.jsonPath().get("userId"), 1);
        Assertions.assertEquals((Integer) response.jsonPath().get("id"), 101);

    }
    @Test
    public void testGetAllResources() {
        logger.info("Get a specific resource using GET method on - " + Config.Input.BASE_URI.getValue() + Config.Input.ENDPOINT.getValue());
        Response response = given()
                .when()
                .get(Config.Input.ENDPOINT.getValue() + "/1")
                .then()
                .extract().response();
        logger.info("Received response body as \n" + response.asPrettyString());
        assertEquals(response.statusCode(), 200);
    }
    @Test
    public void testUpdateResource() throws InterruptedException {
        logger.info("Update a resource using PUT method on - " + Config.Input.BASE_URI.getValue() + Config.Input.ENDPOINT.getValue());
        Response response = given()
                .body(JsonPlaceHolderRequestSpecification.put(1,"Mr","QA Rocks",27).toString())
                .when()
                .put(Config.Input.ENDPOINT.getValue() + "/27")
                .then()
                .extract().response();
        logger.info("Received response body as \n" + response.asPrettyString());
        assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.jsonPath().get("title"), "Mr");
        Assertions.assertEquals(response.jsonPath().get("body"), "QA Rocks");
        Assertions.assertEquals((Integer) response.jsonPath().get("userId"), 27);
        Assertions.assertEquals((Integer) response.jsonPath().get("id"), 27);
    }

    @Test
    public void testDeleteResource() throws InterruptedException {
        logger.info("Remove a resource using DELETE method on - " + Config.Input.BASE_URI.getValue() + Config.Input.ENDPOINT.getValue());
        Response response = given()
                .when()
                .delete(Config.Input.ENDPOINT.getValue() + "/27")
                .then()
                .extract().response();
        logger.info("Received response body as \n" + response.body().asString());
        assertEquals(response.statusCode(), 200);
        assertEquals(response.body().asString(), "{}");
    }
}
