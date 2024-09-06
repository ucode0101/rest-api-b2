package com.ucodeacademy.day_02_path_query_param_token_log;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class AddNewContact {

    // we need to declare the String in the class level, so we can access it from any test method
    private static String token = null;

    @BeforeClass
    public static void setUps(){
        // baseURI is just a String variable that comes from RestAssured
        baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        token= GenerateToken.loginToGetToken();

    }

    String newContact = """
            {
                "firstName": "Jame",
                "lastName": "Jame",
                "birthdate": "1980-09-09",
                "email": "jamesj@fake.com",
                "phone": "898989898",
                "street1": "199 Main St.",
                "street2": "Apartment A",
                "city": "Anytown",
                "stateProvince": "KS",
                "postalCode": "12345",
                "country": "USA"
            }
            """;


    @Test
    public void addNewContact(){

        Response response =
                given().auth().oauth2(token)
                        .contentType(ContentType.JSON)
                        .body(newContact)
                        .post("/contacts");

        Assert.assertEquals(201, response.getStatusCode());

        String contactId = response.path("_id");

        System.out.println(contactId);

        System.out.println("==============================================");

        response.prettyPrint();

    }

}
