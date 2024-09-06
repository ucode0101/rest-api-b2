package com.ucodeacademy.day_02_path_query_param_token_log;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GenerateToken {

    public static String endpoint = "https://thinking-tester-contact-list.herokuapp.com/users/login";
    public static String logins = """
             {
                 "email": "ucode@test.com",
                 "password": "testing123"
             }
            """;


    public static String loginToGetToken(){
        // we did import static io.restassured.RestAssured.given;
        Response response = given().contentType(ContentType.JSON)
                .body(logins)
                .when().post(endpoint);

        // get the token, store in a string variable
        String token = response.path("token");
        return  token;

        // directly return the token without storing it in a variable
       //return response.path("token");

    }

    @Test
    public void getAllContacts(){
        // Generate token
        String token = GenerateToken.loginToGetToken();

        // adding token in the request header
//        Response response = given().header("Authorization", "Bearer "+token)
//                .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");

        // the simpler way
        Response response = given().
                auth().
                oauth2(token).
                when().
                get("https://thinking-tester-contact-list.herokuapp.com/contacts");

        response.prettyPrint();
    }

}
