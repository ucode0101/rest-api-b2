package com.ucodeacademy.day_01_simple_request_and_validation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class SimpleGetRequest {

    @Test
    public void getAllProducts(){

        // we can directly send get request without storing the response or doing any validation
        //RestAssured.get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products");

        // Send the request and store the response in response including body, header, status code, etc...
        // All the information about the request is stored in 'response'
        Response response = RestAssured.get("https://fruitshop2-predic8.azurewebsites.net/shop/v2/products");

        // print status code using statusCode();
        System.out.println("Status code is: "+ response.statusCode());

        // print status code using getStatusCode()
        System.out.println("Status code is: "+ response.getStatusCode());

        System.out.println(response.statusLine());

        // get/print headers from the response
        System.out.println("Headers: "+ response.getHeaders());

        // get the date from response header
        System.out.println("Date from response header: "+ response.getHeader("Date"));

        // get content type
        System.out.println(response.getContentType());
        System.out.println(response.contentType());

        // get content type using getHeader();
        System.out.println(response.getHeader("Content-Type"));

        System.out.println("===============================");

        System.out.println(response.asString());


    }
}
