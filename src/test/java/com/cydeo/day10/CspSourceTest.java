package com.cydeo.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;

public class CspSourceTest {
    @ParameterizedTest
    @CsvSource({
            "NY", "New York",
            "CO", "Denver",
            "VA", "Fairfax",
            "MA", "Boston",
            "MO", "Annapolis"
    })
    public void test1(String state, String zipcode) {
        System.out.println("state = " + state);
        System.out.println("zipcode = " + zipcode);
        System.out.println(" ---------- ");
        given().baseUri("https://api.zippopotam.us")
                .pathParam("state", state)
                .pathParam("city", zipcode)
                .when().get("/us/{state}/{city}").prettyPeek();
    }
}
