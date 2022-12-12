package com.cydeo.day06;

import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PO1_HrDezerelization extends HrTestBase {
    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */
    @DisplayName("GET /locations to deserilization into Java Collections")
    @Test
    public void test1(){
        JsonPath jsonPath = given().log().uri()
                .when().get("/locations")
                .then()
                .statusCode(200)
                .extract().response().jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");
        Map<String,Object> firstMap = jsonPath.getMap("items[0]");
        System.out.println("firstMap = " + firstMap);

        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        Map<String,Object> firstMaoLinks = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstMaoLinks = " + firstMaoLinks);
        // how to get href value from firstMapLinks
        System.out.println("firstMaoLinks.get(\"href\") = " + firstMaoLinks.get("href"));

        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String,Object>> allLocationsMap = jsonPath.getList("items");
        for (Map<String,Object> eachLocations : allLocationsMap) {

        }

        System.out.println("====== FIRST LOCATION ======");
        System.out.println("allLocationsMap.get(0) = " + allLocationsMap.get(0));

        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("allLocationsMap.get(0).get(\"location_id\") = " + allLocationsMap.get(0).get("location_id"));

        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println("allLocationsMap.get(0).get(\"country_id\") = " + allLocationsMap.get(0).get("country_id"));

        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        System.out.println("allLocationsMap.get(0).get(\"links\") = " + allLocationsMap.get(0).get("links"));
        List<Map<String,Object>> links = (List<Map<String,Object>>) allLocationsMap.get(0).get("links");
        System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("href"));

        System.out.println("====== LAST LOCATION ID ======");
        System.out.println("allLocationsMap.get(allLocationsMap.size()-1).get(\"location_id\") = " + allLocationsMap.get(allLocationsMap.size() - 1).get("location_id"));

    }
}
