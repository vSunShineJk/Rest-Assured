package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class PO3_SpartanWithResponsePath extends SpartanTestBase {
    @DisplayName("Get Spartan With Response path")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id","10")
                .when().get("api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        int id = response.path("id");
        var name = response.path("name");
        var gender = response.path("gender");
        var phone = response.path("phone");

        System.out.println("id = " + id);

        var address = response.path("address");
        System.out.println("address = " + address);

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936L,phone);
    }

    @DisplayName("Get all spartans")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

       // response.prettyPrint();

    }

}
