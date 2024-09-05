package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class GetRequestAndValidate {

    @Test
    public void getRequestAndValidate(){
        // send get Request and store response
        Response response = RestAssured.given()
                .get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products");

        //verify status code
        Assert.assertEquals("Failed", 200, response.getStatusCode());


        // verify content type
        Assert.assertEquals("Failed", "application/json", response.getContentType());

        // return/print body as String
        System.out.println(response.asString());

        System.out.println("======================================");

        // return/print body as pretty string
        System.out.println(response.asPrettyString());

        System.out.println("============================================");

        // return/print response pretty print
        response.prettyPrint();

        System.out.println("==============================================");

        // return/print response as pretty peak()
        response.prettyPeek();

        // get response body as String
        // you can directly print or store it in a String
        System.out.println(response.body().asString());

        System.out.println("====================================");

        // get response body as pretty String
        // you can directly print or store it in a String
        String str = response.body().asPrettyString();
        System.out.println(str);




    }
}
