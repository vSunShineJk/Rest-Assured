package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class PO1_HrWithJsonPath extends HrTestBase {
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("limit",200)
                .when().get("/employees");

        response.prettyPrint();

        assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        List<String> allEmails = jsonPath.getList("items.email");
        System.out.println("allEmails = " + allEmails);
        System.out.println("allEmails.size() = " + allEmails.size());

//        System.out.println("====================");
//        List<String> emailsIT = jsonPath.getList("items.findALl {it.job_id==\"IT_PROG\"}.email");
//        System.out.println("emailsIT = " + emailsIT);
//        System.out.println("emailsIT.size() = " + emailsIT.size());

        System.out.println("=================");
        List<String> allEmpsSalaryMoreThan10 = jsonPath.getList("items.findAll {it.salary>=10000}.first_name");
        System.out.println("allEmpsSalaryMoreThan10 = " + allEmpsSalaryMoreThan10);

        System.out.println("jsonPath.getString(\"items.max{itt.salary}\") = " + jsonPath.getString("items.max{it.salary}"));
        System.out.println("jsonPath.getString(\"items.min{itt.salary}\") = " + jsonPath.getString("items.min{it.salary}"));
    }


}
