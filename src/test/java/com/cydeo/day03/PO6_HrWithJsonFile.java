package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class PO6_HrWithJsonFile extends HrTestBase {

    @DisplayName("Get all countries")
    @Test
    public void test1(){
        Response response = get("/countries");

        response.prettyPrint();

        assertEquals(200,response.statusCode());

        // create JsonPath
        JsonPath jsonPath = response.jsonPath();

        // get 3rd country's information
        System.out.println("jsonPath.getString(\"item[2]\") = " + jsonPath.getString("items[2]"));
        // can we store them to a Map
        // yes

        // get 3rd country's name
        String countryThird = jsonPath.getString("items[2].country_name");
        System.out.println(countryThird);

        // get 2nd and 3rd country name
        System.out.println("jsonPath.getString(\"item[2].country_name\") = " + jsonPath.getString("items[2,3].country_name"));

        // getting all countries name where is region_id is 2
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("list = " + list);

        /*
            it refers each item in items list
         */
    }

}
