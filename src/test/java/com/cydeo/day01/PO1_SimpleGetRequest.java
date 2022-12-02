package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PO1_SimpleGetRequest {

    String url = "http://34.203.212.11:8000/api/spartans";

    /**
     * When user request to /opi.spartans endpoint
     * then user should be able to see status code is 200
     * And print out responses body into screen
     */

    @Test
    public void simpleGetRequest(){
        // send request to the url and get response as Response interface
        Response response = RestAssured.get(url);

        // both same, no difference
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.statusLine() = " + response.statusLine());

        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200,actualStatusCode);

        // how to print into screen?
        response.prettyPrint();
    }

}
