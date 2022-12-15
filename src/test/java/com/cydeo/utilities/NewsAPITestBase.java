package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public abstract class NewsAPITestBase {
    @BeforeAll
    public static void init1(){
        RestAssured.baseURI = "http://newsapi.org/v2";
    }
}
