package com.cydeo.day07;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
public class P02_SpartanPost extends SpartanTestBase {
    @DisplayName("POST Spartan with String body")
    @Test
    public void test1() {
        String requestBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Mike\",\n" +
                "  \"phone\": 1234567890\n" +
                "}";

        String expectedMessage = "A Spartan is Born!";

        JsonPath jsonPath = given().accept(ContentType.JSON)  // API send me response in json format
                .contentType(ContentType.JSON) // API I am sending body in JSON format
                .body(requestBody)  // This is that we want to POST in API
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().jsonPath();


        assertEquals(expectedMessage, jsonPath.getString("success"));
        assertEquals("Mike", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("1234567890", jsonPath.getString("data.phone"));

        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

        // If you want to do assertion with GET Request we can use it as Path param to specify

        /*
         {
          "success": "A Spartan is Born!",
          "data": {
                   "id": 379,
                   "name": "Mike",
                   "gender": "Male",
                   "phone": 1234567890
                  }
          }
         */
    }

    @Test
    public void test2(){

        Map<String, Object> requestedBody = new LinkedHashMap<>();

        requestedBody.put("name","James Bond");
        requestedBody.put("gender","Male");
        requestedBody.put("phone",12345678910L);

        JsonPath jsonPath =
                given()
                        .accept(ContentType.JSON).log().body()
                        .contentType(ContentType.JSON)
                        .body(requestedBody)
                .when()
                        .post("/api/spartans").prettyPeek()
                .then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath();

        String expectedMsg = "A Spartan is Born!";
        assertEquals(expectedMsg,jsonPath.getString("success"));
    }
}
