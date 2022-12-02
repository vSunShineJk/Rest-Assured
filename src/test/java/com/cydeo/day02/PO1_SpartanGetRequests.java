package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PO1_SpartanGetRequests {

    String url = "http://34.203.212.11:8000";

    /*
     * Given accept content type is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And content type should be application json
     */

    @DisplayName("GET All Spartans")
    @Test
    public void getAllSpartans(){
        Response response = RestAssured
                                    .given()
                                    .accept(ContentType.JSON)
                                    .when()
                                    .get(url + "/api/spartans");
        // print response
        /*response.prettyPrint();*/

        // how to get status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200,actualStatusCode);

        // how to get contentType
        String actualContentType = response.contentType();
        Assertions.assertEquals("application/json",actualContentType);

        // how to get header info
        String connection = response.header("Connection");
        System.out.println("connection = " + connection);

        // get content type wit header
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        // get data header
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        // how can we verify data is exist?
        boolean dateVerification = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(dateVerification);
    }

    @DisplayName("GET Single Spartan")
    @Test
    public void getSpartan(){

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when().get(url + "/api/spartans/5");

        // Verify status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200,actualStatusCode);

        // Verify contentType is application json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
        Assertions.assertEquals("application/json",response.header("Content-Type"));
        Assertions.assertEquals(ContentType.JSON.toString(),response.header("Content-Type"));

        // Verify body contains Blythe
        Assertions.assertTrue(response.body().asString().contains("Blythe"));

        // if we don't have related name
        System.out.println("response.header(\"keepAlive\") = " + response.header("keepAlive"));

    }
}
