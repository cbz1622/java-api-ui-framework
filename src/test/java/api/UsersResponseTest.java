package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.postoffice.api.config.Config;
import org.postoffice.api.requestSpecification.JsonPlaceHolderRequestSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersResponseTest {
    static RequestSpecification requestSpecs;

    @BeforeAll
    public void setup() {
        requestSpecs = JsonPlaceHolderRequestSpecification.getRequestSpecification();
        if (requestSpecs == null) {
            throw new RuntimeException("Request Spec is null");
        }
        RestAssured.requestSpecification = requestSpecs;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void testCreateResource() throws InterruptedException {
        Response response = given()
                .body(JsonPlaceHolderRequestSpecification.posts("Mr","QA Champions",1).toString())
                .when()
                .post(Config.Input.ENDPOINT.getValue())
                .then()
                .extract().response();
        System.out.print(response.asPrettyString());
        Assertions.assertEquals(response.statusCode(), 201);
        Assertions.assertEquals(response.jsonPath().get("title"), "Mr");
        Assertions.assertEquals(response.jsonPath().get("body"), "QA Champions");
        Assertions.assertEquals((Integer) response.jsonPath().get("userId"), 1);
        Assertions.assertEquals((Integer) response.jsonPath().get("id"), 101);
    }
    @Test
    public void testGetAllResources() {
        Response response = given()
                .when()
                .get(Config.Input.ENDPOINT.getValue())
                .then()
                .extract().response();
        assertEquals(response.statusCode(), 200);
        System.out.print(response.asPrettyString());
    }
    @Test
    public void testUpdateResource() throws InterruptedException {
        Response response = given()
                .body(JsonPlaceHolderRequestSpecification.put(1,"Mr","QA Rocks",27).toString())
                .when()
                .put(Config.Input.ENDPOINT.getValue() + "/27")
                .then()
                .extract().response();
        assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.jsonPath().get("title"), "Mr");
        Assertions.assertEquals(response.jsonPath().get("body"), "QA Rocks");
        Assertions.assertEquals((Integer) response.jsonPath().get("userId"), 27);
        Assertions.assertEquals((Integer) response.jsonPath().get("id"), 27);
        System.out.print(response.asPrettyString());
    }

    @Test
    public void testDeleteResource() throws InterruptedException {
        Response response = given()
                .when()
                .delete(Config.Input.ENDPOINT.getValue() + "/27")
                .then()
                .extract().response();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.body().asString(), "{}");
        System.out.print(response.asPrettyString());
    }
}
