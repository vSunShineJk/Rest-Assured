package com.cydeo.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class P03_ValueSourceTest {
    @ParameterizedTest
    @ValueSource(ints = {10,20,30,40,50})
    public void test1(int numbers){
        System.out.println(numbers);
        Assertions.assertTrue(numbers < 100);
    }

    @ParameterizedTest(name = "{index} name is {0}")
    @ValueSource(strings = {"rose","mike", "king"})
    public void test2(String name){
        // {index} ---> it will print out in console as test name with their index
        // {0} --->
        System.out.println("name: " + name);
    }

    @ParameterizedTest
    @ValueSource(ints = {22030,22031,22032,22033,22034,22035,22036})
    public void test2(int nums){

        // Send GET request https://zippopotam.us/us/{zipcode}
        // with these zipcodes: 22030, 22031, 22032, 22033, 22034, 22035, 22036
        // check status code 200

        given().pathParam("zipcode",nums)
                .when()
                .get("https://zippopotam.us/us/{zipcode}")
                .then()
                .statusCode(200);
    }
}
