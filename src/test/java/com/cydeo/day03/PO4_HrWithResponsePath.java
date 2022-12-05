package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PO4_HrWithResponsePath extends HrTestBase {
    @DisplayName("Get request to countries with using response pth")
    @Test
    public void tes1(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        // print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        // print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        // print second country_id
        System.out.println("response.path(\"items[1].country_id\") = " + response.path("items[1].country_id"));
        // print 4ht element country name
        System.out.println("response.path(\"items[3].country_id\") = " + response.path("items[3].country_id"));
        // print 4th element href
        System.out.println("response.path(\"items[3].links.href\") = " + response.path("items[3].links.href"));
        // get me all countries name
        List<String> allCountryName = response.path("items.country_name");
        System.out.println("allCountryName = " + allCountryName);

        //  verify al region_id is 2
        List<Integer> allRegionIds = response.path("items.region_id");
        for (int each : allRegionIds) assertEquals(2,each);
        // throw stream
        assertTrue(allRegionIds.stream().allMatch(each -> each == 2));

        /*
            Send a Get request to /employees to see only job_id = IT_PROG
            Query Param:
                q = {"job_id":"IT_PROG"}
            then asser all job_id are It_PROg
         */
    }

    @Test
    public void test2(){
        Response response = given()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        List<String> jobId = response.path("job_id");
        System.out.println("jobId = " + jobId);
    }
}
