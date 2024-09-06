package com.ucodeacademy.day_02_path_query_param_token_log;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeleteRequest {

    @BeforeClass
    public static void setUps(){
        // baseURI is just a String variable that comes from RestAssured
        baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";

    }

    @Test
    public void deleteProduct(){
        given().pathParams("id", 15)
                .when().delete("/products/{id}")
                .then()
                .log().body()
                .log().status()
                .body("success", is("Deleted"));

    }
}
