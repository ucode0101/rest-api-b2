package com.ucodeacademy.day_02_path_query_param_token_log;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SendRequestWithToken {

    // we need to declare the String in the class level, so we can access it from any test method
    private static String token = null;

    @BeforeClass
    public static void setUps(){
        // baseURI is just a String variable that comes from RestAssured
        baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        token= GenerateToken.loginToGetToken();

    }

    @Test
    public void getAllContacts(){
        // One way without storing the response
        // send GET request and do validation, print logs on the console
        given().auth().oauth2(token)
                .when()
                .get("/contacts")
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .log().headers();
    }

    @Test
    public void getAllContacts2(){
        // Another way is to send request, store the response
        // and do the validation

        Response response =
                given().auth().oauth2(token)
                        .when().get("/contacts");

        // verify the status code is 200
        Assert.assertEquals(200, response.getStatusCode());

        // verify content type
        Assert.assertEquals("application/json; charset=utf-8",  response.getContentType());

        // get/retrieve any fist name from response body (Array JSON)
        String firstName = response.path("[4].firstName");
        System.out.println("Random first name: "+ firstName);

        // get/retrieve contact id
        String id = response.path("[4]._id");
        System.out.println("Random ID: "+ id);

        System.out.println("============================================");

        // print an Object at index 4 from Array JSON
        System.out.println(response.path("[4]").toString());

        System.out.println("===================================");

        // print the whole response body in nice looking format
        response.prettyPrint();


    }
    String contactId = "66d11e8ee58d2200130d9a65";
    @Test
    public void getContactById(){
        given().auth().oauth2(token)
                .when()
                .get("/contacts/"+contactId)
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .log().headers();

    }

    @Test
    public void getContactById2(){
        Response response =
                given().auth().oauth2(token)
                        .when().get("/contacts/"+contactId);

        //verify status code
        Assert.assertEquals(200, response.getStatusCode());

        // verify content type
        Assert.assertTrue(response.getContentType().contains("application/json"));

        // get id
        String id = response.path("_id");
        // get first name
        String firstName = response.path("firstName");

        System.out.println(id);
        System.out.println(firstName);

        System.out.println("================================");

        response.prettyPrint();





    }


}
