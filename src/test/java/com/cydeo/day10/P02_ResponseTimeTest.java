package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P02_ResponseTimeTest extends SpartanAuthTestBase {
    @DisplayName("GET /api/spartans to get response time")
    @Test
    public void test1(){
        var response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(both(greaterThan(2000L)).and(lessThanOrEqualTo(10000L))).extract().response();// response will store information about response time as well. So it is actual

        System.out.println("seconds: "+response.getTimeIn(TimeUnit.SECONDS));
        System.out.println("milli: "+response.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println("micro: "+response.getTimeIn(TimeUnit.MICROSECONDS));
        System.out.println("nano: "+response.getTimeIn(TimeUnit.NANOSECONDS));
    }
}
