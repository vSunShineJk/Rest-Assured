package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class ZipCodeTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://www.zippopotam.us";
    }
}
