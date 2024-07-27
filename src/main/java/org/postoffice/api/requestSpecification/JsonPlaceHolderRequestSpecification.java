package org.postoffice.api.requestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.postoffice.api.config.Config;
import static io.restassured.config.ConnectionConfig.connectionConfig;
import static io.restassured.config.HttpClientConfig.httpClientConfig;

public class JsonPlaceHolderRequestSpecification {
    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.Input.BASE_URI.getValue())
                .setContentType(Config.Input.HEADER_CONTENT_TYPE.getValue())
                .setAccept(Config.Input.HEADER_ACCEPT.getValue())
                .setConfig(io.restassured.RestAssured.config()
                        .connectionConfig(connectionConfig().closeIdleConnectionsAfterEachResponse()))
                .setConfig(io.restassured.RestAssured.config()
                        .httpClient(httpClientConfig()
                                .setParam("http.connection.timeout", Integer.parseInt(Config.Input.CONNECTION_TIMEOUT.getValue()))))
                .build();
    }

    public static JSONObject posts(String title, String body, int userId) {
        return new JSONObject()
                .put("title", title)
                .put("body",body)
                .put("userId", userId);
    }

    public static JSONObject put(int id, String title, String body, int userId) {
        return new JSONObject()
                .put("id", id)
                .put("title", title)
                .put("body",body)
                .put("userId", userId);
    }

}
