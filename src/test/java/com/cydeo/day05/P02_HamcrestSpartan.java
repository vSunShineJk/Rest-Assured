package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P02_HamcrestSpartan extends SpartanTestBase {

    /*
    Given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */


    @DisplayName("Get Single Spartan with Hamcrest")
    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",15).
                when().get("/api/spartans/{id}").
                then().log().ifValidationFails()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .and() // these are filler keywords / syntatic sugar to increase readability
                // .statusCode(is(200)) --> if you wanna use with Matchers method you can use to increase readability
                .contentType("application/json")
                .assertThat() // these are again syntatic sugar just to increase readability
                .and()
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106));

        /*
        REQUEST
            given().log().all() --> it will give all inforamtion anout your request (path/query params , URI , body etc )
                  .method()
                  .uri()
                  .parameters() ......
        RESPONSE
             then().log().all() --> it will give all response information
                         .ifValidationFails() --> it will print all response if one of the validation fails
         */

    }


    @DisplayName("Get Single Spartan with Hamcrest")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15).
                when().get("/api/spartans/{id}").prettyPeek().
                then()
                .statusCode(200)
                // .statusCode(is(200)) --> if you wanna use with Matchers method you can use to increase readability
                .contentType("application/json")
                .body("id", is(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106))
                .extract().response();

        int id = response.path("id");
        System.out.println("id = " + id);

        // HOW TO PRINT RESPONSE BODY
        /*
            - response.prettyPrint() (String) ---> it is printing response body into screen
            - response.prettyPeek() (Response) ---> it will print response into screen, returns Response   and allows us to continue chaining
         */


        // HOW TO EXTRACT DATA AFTER DOING VALIDATION WITH HAMCREST ?
        /*
        - extract() --> method will help us to STORE data after doing verification as
                response()
                  OR
                jsonPath()
        - Why we need to extract ?
            - Assume that we are gonna do verification against UI/DB.In that case I need to get data from API after doing verification
            - SO we need to sometimes List of names / ids etc to check
            - That is why we need to initilaize as Response or JSonPAth (Since we know how to get data through this objects )
               to get realted data taht you wanna verfiy
         */
    }
    @DisplayName("Get Single Spartan with Hamcrest")
    @Test
    public void test3() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", 15).
                when().get("/api/spartans/{id}").prettyPeek().
                then()
                .statusCode(200)
                // .statusCode(is(200)) --> if you wanna use with Matchers method you can use to increase readability
                .contentType("application/json")
                .body("id", is(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106))
                .extract().response().jsonPath();

        int id = jsonPath.getInt("id");
        System.out.println("id = " + id);
    }
}
