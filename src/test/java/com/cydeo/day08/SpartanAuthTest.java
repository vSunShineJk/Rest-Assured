package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanAuthTest extends SpartanAuthTestBase {
    @DisplayName("GET /spartans as GUEST User --> EXPECT --> 401")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .auth().basic("user","user") // basic authorization credentials
        .when().get("api/spartans")
        .then().log().ifValidationFails().statusCode(200);

    }

    @DisplayName("GET /spartans as EDITOR User --> EXPECT --> 401")
    @Test
    public void test3(){

        given().pathParam("id",56)
                .auth().basic("editor","editor") // basic authorization credentials
                .when().delete("api/spartans/{id}").then().statusCode(403)
                .body("error",is("Forbidden"));

    }

    @DisplayName("GET /spartans as ADMIN User --> EXPECT --> 204")
    @Test
    public void test4(){

        given().pathParam("id",79)
                .auth().basic("admin","admin") // basic authorization credentials
                .when().delete("api/spartans/{id}").then().statusCode(204);

    }
}
