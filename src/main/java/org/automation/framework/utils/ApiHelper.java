package org.automation.framework.utils;


import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public final class ApiHelper {

    /**
     * Perform GET request
     *
     * @param endpoint the API endpoint (e.g., "geowidget/v1/points")
     * @param params   query parameters to append to the request
     * @return the Response from API
     */
    public static Response get(String endpoint, Map<String, Object> params) {
        return given()
                .queryParams(params)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();
    }
}
