package com.ucodeacademy.day_02_path_query_param_token_log;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;


public class GetRequest {
    //static String baseURL = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";

    @BeforeClass
    public static void setUps(){
        // baseURI is just a String variable that comes from RestAssured
        baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";

    }

    @Test
    public void getAllVendors(){
        // get all vendors and print in the console response logs (all details)
        //log() (prettyPeak) response

        //given().get(baseURI+"/vendors").then().log().all(); // baseURI + "/pathParam"


        // baseURI will be included automatically and the path param will be added
        // at the end of it automatically
        given().get("/vendors").then().log().all();

    }

    @Test
    public void getVendorById(){
        // Using pathParams("key", "value")
//        given().pathParams("id",3).when().get("/vendors/{id}")
//                .then().log().body();

        // the way we did in postman, works too
        given().when().get("/vendors/3").then().log().body();
    }

    @Test
    public void getProductsQueryParam(){
        given()
                .queryParam("name","Orange")
                .queryParam("start",2)
                .queryParam("limit", 100)
                .when().get("/products")
                .then().log().body();
    }




}
