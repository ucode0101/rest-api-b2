package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class SimplePutRequest {
    @Test
    public void putRequest(){
        String endpoint = "https://fruitshop2-predic8.azurewebsites.net/shop/v2/products";

        String putBody = """
                {
                  "id": 27,
                  "name": "Banana,",
                  "price": 1.39
                }
                """;

        Response response = RestAssured.given().
                contentType(ContentType.JSON).
                body(putBody)
                .put(endpoint+"/27");

       // System.out.println(response.asPrettyString());
        //System.out.println(response.asString());

        response.prettyPrint();

        System.out.println("========================");

        response.prettyPeek();

    }
}
