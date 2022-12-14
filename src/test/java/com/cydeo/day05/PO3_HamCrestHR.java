package com.cydeo.day05;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PO3_HamCrestHR extends HrTestBase {
    @Test
    public void test1(){
        /*
            Given accept type is Json
            And parameters q = {"job_id":"IT_PROG")
            When users send a GET request to "/employees"
            Then status code is 200
            And Content type is application/json
            Verify
                - each employees has manager
                - each employees working as IT_PROG
                - each of them getting salary greater than 3000
                - first names are .... (find proper method to check list against list)
                - emails without checking order (provide emails in different order, just make sure it has same emails)
        */
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given().accept(ContentType.JSON)
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("items.manager_id",everyItem(notNullValue()))
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.salary",everyItem(greaterThan(3000)))
                .body("items.first_name",equalTo(names));
                //.body("items.email",contains("DAUSTIN", "ANUHOLD", "BERNST", "VPATABAL","DLORENTZ"));
    }

    @Test
    public void test2(){
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/regions").prettyPeek()
                .then()
                .statusCode(200)
                .header("Date", notNullValue())
                .body("items[0].region_name", is("Europe"))
                .body("items[0].region_id", is(1))
                .body("items", hasSize(4))
                .body("items.region_name", everyItem(notNullValue()))
                //.body("items.region_name", containsInRelativeOrder("Europe","Americas","Asia","Middle Eas and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4))
                .extract().jsonPath();

        // print all regions name
        List<String> allRegionsName = jsonPath.getList("items.region_name");
        System.out.println("allRegionsName = " + allRegionsName);
    }
}
