package com.cydeo.day09;

import com.cydeo.utilities.NewsAPITestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P01_NewsAPI extends NewsAPITestBase {
    @Test
    public void test1() {
        given().queryParam("q", "bitcoin")
                .queryParam("apiKey", "61d6cc9e13cc4626afbd54bd01fe7123")
                .when().get("/everything").prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void test2() {
        given().queryParam("q", "bitcoin")
                .header("x-api-key","61d6cc9e13cc4626afbd54bd01fe7123")
                .when().get("/everything").prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void test3() {
        given().queryParam("q", "bitcoin")
                .header("Authorization","61d6cc9e13cc4626afbd54bd01fe7123")
                .when().get("/everything").prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void test4() {
        given().queryParam("country", "us")
                .header("Authorization","Bearer 61d6cc9e13cc4626afbd54bd01fe7123")
                .when().get("/top-headlines").prettyPeek()
                .then()
                .statusCode(200);
    }
}
