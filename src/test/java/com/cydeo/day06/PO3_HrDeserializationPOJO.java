package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
public class PO3_HrDeserializationPOJO extends HrTestBase {
    @DisplayName("Get regions to deserialization to POJO + LOMBOK + ")
    @Test
    public void test1() {
        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region firstRegion = jsonPath.getObject("items[0]", Region.class);

        System.out.println(firstRegion);

        System.out.println("firstRegion.getLinks().get(0) = " + firstRegion.getLinks().get(0));

        System.out.println("firstRegion.getRegion_name() = " + firstRegion.getRegionName());
    }

    @Test
    public void test2() {
        JsonPath jsonPath = get("/employees").then().statusCode(200).extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);

        System.out.println(employee1);
    }
}
