package com.school21.restassured;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

public abstract class RestAssuredTestBase {
    
    protected static final String BASE_URL = "https://reqres.in/api";
    protected static final String USERS_ENDPOINT = "/users";
    
    protected RequestSpecification requestSpec;
    
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        
        // Настраиваем ObjectMapper для игнорирования неизвестных полей
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (cls, charset) -> mapper
                ));
        
        requestSpec = given()
                .header("Content-Type", "application/json")
                .log().ifValidationFails();
    }
    
    protected void validateStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
    
    protected Response getRequest(String endpoint) {
        return requestSpec.when().get(endpoint).then().extract().response();
    }
    
    protected Response getRequestWithParams(String endpoint, String paramName, Object paramValue) {
        return requestSpec.param(paramName, paramValue)
                .when().get(endpoint)
                .then().extract().response();
    }
    
    protected Response postRequest(String endpoint, Object body) {
        return requestSpec.body(body)
                .when().post(endpoint)
                .then().extract().response();
    }
    
    protected Response putRequest(String endpoint, Object body) {
        return requestSpec.body(body)
                .when().put(endpoint)
                .then().extract().response();
    }
    
    protected Response deleteRequest(String endpoint) {
        return requestSpec.when().delete(endpoint).then().extract().response();
    }
}
