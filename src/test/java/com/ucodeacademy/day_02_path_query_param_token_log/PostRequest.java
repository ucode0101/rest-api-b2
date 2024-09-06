package com.ucodeacademy.day_02_path_query_param_token_log;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PostRequest {

    @BeforeClass
    public static void setUps(){
        // baseURI is just a String variable that comes from RestAssured
        baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";

    }

    String postBody = """
            {
              "name": "Watermelon",
              "price": 7.99
            }
            """;

    @Test
    public void addNewProduct(){

        given().contentType(ContentType.JSON)
                .body(postBody)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .body("name", is("Watermelon"))  // retrieve name value, and verify it is Watermelon
                .log().body();

    }
}
