package com.cydeo.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P03_MovieXML {
    @Test
    public void test1(){

        Response response = given().queryParam("t","Superman")
                .queryParam("r","xml")
                .queryParam("apikey","81815fe6")
                .when().get("http://www.omdbapi.com");

        // create XML path
        XmlPath xmlPath = response.xmlPath();
        // get year attribute
        System.out.println("xmlPath.getString(\"root.movie.@year\") = " + xmlPath.getString("root.movie.@year"));
        // get year title
        System.out.println("xmlPath.getString(\"root.movie.@title\") = " + xmlPath.getString("root.movie.@title"));
        // get year genre
        System.out.println("xmlPath.getString(\"root.movie.@genre\") = " + xmlPath.getString("root.movie.@genre"));
        // get year write
        System.out.println("xmlPath.getString(\"root.movie.@writer\") = " + xmlPath.getString("root.movie.@writer"));

    }

    @Test
    public void test2(){

        /*
            HOME WORK
            https://www.omdbapi.com/?apikey=81815fe6&t=Batman&r=xml
            Try to get all year information
         */

        Response response = given().queryParam("t","Harry Potter")
                .queryParam("r","xml")
                .queryParam("type","series")
                .queryParam("apikey","81815fe6")
                .when().get("http://www.omdbapi.com");


        // create XML path
        XmlPath xmlPath = response.xmlPath();

        /*
        // get year attribute
        System.out.println("xmlPath.getString(\"root.movie.@year\") = " + xmlPath.getString("root.movie.@year"));
        // get year title
        System.out.println("xmlPath.getString(\"root.movie.@title\") = " + xmlPath.getString("root.movie.@title"));
        // get year genre
        System.out.println("xmlPath.getString(\"root.movie.@genre\") = " + xmlPath.getString("root.movie.@genre"));
        // get year write
        System.out.println("xmlPath.getString(\"root.movie.@writer\") = " + xmlPath.getString("root.movie.@writer"));
         */

        String[] attributes = {"title","year","rated","released","runtime","genre","director","writer","actors","plot","language","country","awards","poster","metascore","imdbRating","imdbVotes","imdbID","type"};
        for (String each : attributes) System.out.println(each + ": " + xmlPath.getString("root.movie.@"+each));


    }
}
