package com.cydeo.day9;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class P04_JsonSchemeValidation extends SpartanTestBase {
    @DisplayName("GET /api/spartans/{id} to validate with JsonSchemeValidation")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .pathParam("id", 45)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200).contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
    }

    @DisplayName("GET /api/spartans/{id} to validate with JsonSchemeValidation")
    @Test
    public void test2(){

        given().contentType(ContentType.JSON)
                .pathParam("id",45)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanScheme.json")));

    }

    // GET api/spartans/search to validate with JsonSchemaValidator
    @Test
    public void test3() {
        given().accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200).contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SearchSpartansSchema.json")));
    }

}
