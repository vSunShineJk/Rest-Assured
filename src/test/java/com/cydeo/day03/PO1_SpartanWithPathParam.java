package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PO1_SpartanWithPathParam extends SpartanTestBase {


    @DisplayName("GET Spartan api/spartans/{id} path param with 24")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParams("id",24)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Julio"));
    }


    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParams("id",500)
                .when().get("/api/spartans/{id}");

        assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));
    }


    @DisplayName("Get Request /api/spartans/search with Query Param")
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("gender","Female")
                .and()
                .queryParams("nameContains","e")
                .when().get("/api/spartans/search");

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }


    @DisplayName("Get Request /api/spartans/search with Query Param")
    @Test
    public void test4(){

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .queryParams(queryMap)
                .when().get("/api/spartans/search");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals("application/json",response.contentType());
    }
}
