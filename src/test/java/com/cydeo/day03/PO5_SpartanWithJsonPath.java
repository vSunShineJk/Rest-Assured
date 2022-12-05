package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PO5_SpartanWithJsonPath extends SpartanTestBase {
    @DisplayName("get Spartan withJson path")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",10)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200,response.statusCode());
        System.out.println(response.path("id").toString());

        // we saved response as a JSONPATH OBJECT
        JsonPath jsonPath = response.jsonPath();

        // is it possible to get statusCode/ ContentType/ Headers with JsonPath
        // if you wanna do assertion, still we need to use response obj

        System.out.println(jsonPath.getInt("id"));

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        assertEquals(10,id);

    }
}
