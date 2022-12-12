package com.cydeo.day07;

import com.cydeo.utilities.CydeoTrainingTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class PO1_CydeoTraining extends CydeoTrainingTestBase {
      /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
       // Ignore all non necessray fields
     */

    @DisplayName("GET /student/{id} 2 ")
    @Test
    public void test1() {

        JsonPath jsonPath = given().log().all().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2).
                when().get("/student/{id}").
                then()
                .statusCode(200)
                .extract().jsonPath();
    }
}
