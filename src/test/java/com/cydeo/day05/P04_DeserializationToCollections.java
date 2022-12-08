package com.cydeo.day05;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P04_DeserializationToCollections extends HrTestBase {
    /*
     Given accept type is application/json
     And Path param id = 10
     When i send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
     id > 10
     name>Lorenza
     gender >Female
     phone >3312820936
     */
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10).
                when().get("/api/spartans/{id}").prettyPeek().
                then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        //Approach one ---> with Response

        Map<String,Object> spartanMap= response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);

        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");
        String gender = (String) spartanMap.get("gender");

        System.out.println("id = " + id);

        // Approach second ---> with JSONPATH

        JsonPath jsonPath = response.jsonPath();
        Map<String , Object> jsonPathMap = jsonPath.getMap("");
        System.out.println("jsonPathMap = " + jsonPathMap);

        int idJson = (int) jsonPathMap.get("id");
        String nameJson = (String) jsonPathMap.get("name");

        System.out.println("idJson = " + idJson);


    }

    @DisplayName("GET All Spartans with Java Collections")
    @Test
    public  void test2() {

        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans").
                then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        //Approach one ---> with Response

        List<Map<String,Object>> spartanList = response.as(List.class);


        for (Map<String, Object> eachMap : spartanList) {
            System.out.println("eachMap = " + eachMap);
        }

        // How to find first spartan info
        System.out.println("spartanList.get(0) = " + spartanList.get(0));
        // How to find first spartan name
        System.out.println("spartanList.get(0).get(\"name\") = " + spartanList.get(0).get("name"));
        // How to find first spartan id
        System.out.println("spartanList.get(0).get(\"id\") = " + spartanList.get(0).get("id"));


        // how to store first spartan info as a map with response.as() method
        // if yo uwanna convert only specific part of response with response.as() it does not have any parameters to get as path of
        // json object.That is why we can use in here response.path() method to convert this as a Object.Since we know the type of it
        // we can use and store as Map

        Map<String, Object> firstSpartanMap = response.path("[0]");
        System.out.println("firstSpartanMap = " + firstSpartanMap);



        // Approach second ---> with JSONPATH
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> allSpartanList = jsonPath.getList("");

        for (Map<String, Object> eachSpartan : allSpartanList) {
            System.out.println("eachSpartan = " + eachSpartan);
        }

        // How to find first spartan info
        System.out.println("allSpartanList.get(0) = " + allSpartanList.get(0));
        // How to find first spartan name
        System.out.println("allSpartanList.get(0).get(\"name\") = " + allSpartanList.get(0).get("name"));
        // How to find first spartan id
        System.out.println("allSpartanList.get(0).get(\"id\") = " + allSpartanList.get(0).get("id"));

    }

}