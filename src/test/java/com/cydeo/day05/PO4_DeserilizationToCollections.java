package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;


import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PO4_DeserilizationToCollections extends SpartanTestBase {
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        //Approach one --> with Response
        Map<String,Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);


    }
}
