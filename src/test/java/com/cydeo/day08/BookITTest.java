package com.cydeo.day08;

import com.cydeo.utilities.BookITTestBase;
import com.cydeo.utilities.BookITUtils;
import com.cydeo.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class BookITTest extends BookITTestBase {

//    String email = "lfinnisz@yolasite.com";
//    String password = "lissiefinnis";
    String accessToken = BookITUtils.getToken(ConfigurationReader.getProperty("team-leader-email"),ConfigurationReader.getProperty("team-leader-password"));
    @DisplayName("GET api/campuses")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("api/campuses").prettyPeek()
                .then().statusCode(200);
    }


    @DisplayName("GET /api/users/me")
    @Test
    public void test2(){

        System.out.println(accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("api/users/me").prettyPeek()
                .then().statusCode(200);
    }
}
