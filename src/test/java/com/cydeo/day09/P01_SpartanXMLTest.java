package com.cydeo.day09;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_SpartanXMLTest extends SpartanTestBase {

    /*
    Given accept type application/xml
    When send request to /api/spartans
    Then status code 200
     */
    @Test
    public void test1(){
        given().accept(ContentType.XML)
                .auth().basic("admin","admin")
                .get("/api/spartans")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/xml")
                .body("List.item[0].name",is("Paige"))
                .body("List.item[0].gender",is("Female"));
    }

    @DisplayName("GET /api/spartans with using XMLPath")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();

        System.out.println("xmlPath.getString(\"List.item[0].name\") = " + xmlPath.getString("List.item[0].name"));
        System.out.println("xmlPath.getString(\"List.item[0].gender\") = " + xmlPath.getString("List.item[0].gender"));
        System.out.println("xmlPath.getString(\"List.item[-1].name\") = " + xmlPath.getString("List.item[-1].name"));
        System.out.println("xmlPath.getString(\"List.item.name\") = " + xmlPath.get("List.item.name"));
        System.out.println("xmlPath.getList(\"List.item\").size() = " + xmlPath.getList("List.item").size());

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println();

        for (int i = 0; i < xmlPath.getList("List.item").size(); i++){
            System.out.println("xmlPath.getString(\"List.item[\""+i+"\"].name\") = " + xmlPath.getString("List.item[" + i + "].name"));
        }

    }
}
