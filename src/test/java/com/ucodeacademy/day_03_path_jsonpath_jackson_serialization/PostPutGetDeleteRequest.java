package com.ucodeacademy.day_03_path_jsonpath_jackson_serialization;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class PostPutGetDeleteRequest {

    @BeforeClass
    public static void setUp(){
        baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";
    }

    // declare an int variable for product id
    int productId;


    @Test
    public void AddUpdateReadDeleteProduct(){
        File productBody = new File("product.json");

        // 1. Add a new product
        Response postResponse = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(productBody)
                .when()
                .post("/products");

        //verify status code
        postResponse.then().assertThat().statusCode(201);

        // print the response body
        postResponse.prettyPrint();

        // get/retrieve newly added product ID and store it in productId variable
        // to use it later
        productId = postResponse.path("id");

        System.out.println("==================================");

        // 2. Get the newly add product by its ID, and verify/validate
        Response getResponse = given().accept(ContentType.JSON)
                //.pathParams("id",productId) // using path param to get product by ID
                .when()
                //.get("/products/{id}");
                .get("/products/"+productId); // adding product id at the end

        // verify status code with Built-in assertion
        getResponse.then().assertThat().statusCode(200);

        // verify with JUnit
        Assert.assertEquals(200, getResponse.getStatusCode());

        // print product
        getResponse.prettyPrint();


        System.out.println("====================================");

        // 3. Update the newly added product

        File putBody = new File("product2.json");

        Response putResponse = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(putBody)
                .when()
                .put("/products/"+productId);

        // verify status code
        putResponse.then().assertThat().statusCode(200);

        // print
        putResponse.prettyPrint();

        System.out.println("================================");

        // 4. Partially update newly updated product

        String partialBody = """
                 {
                 "price": 7.89
                 }
                """;

        Response patchResponse = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(partialBody)
                .when()
                .patch("/products/"+productId);

        // verify status code
        patchResponse.then().assertThat().statusCode(200);

        // print
        patchResponse.prettyPrint();


        System.out.println("==================================");

        // 5. Delete the product

        Response deleteResponse = given().accept(ContentType.JSON)
                .when().delete("/products/"+productId);

        // verify status code
        deleteResponse.then().assertThat().statusCode(200);

        // verify response body success message
        Assert.assertEquals("Deleted",deleteResponse.path("success"));

        // print the response body
        deleteResponse.prettyPrint();

        System.out.println("========================================");


        // 6. verify the product is deleted by sending GET request
        // and verify status code and error message

       given().accept(ContentType.JSON)
                .when()
                .get("/products/"+productId)
               .then().assertThat().statusCode(404)
               .log().body();




    }


}
