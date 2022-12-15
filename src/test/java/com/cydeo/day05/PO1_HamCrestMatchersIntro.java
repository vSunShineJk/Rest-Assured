package com.cydeo.day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public class PO1_HamCrestMatchersIntro {
    @Test
    public void numbers(){
        // it comes from Junit5 to make assertion
        assertEquals(9,6+3);

        // Hamcrest Matchers comes from RestAssured library
        assertThat(9, is(6+3));
        assertThat(9,is(equalTo(6+3)));
        assertThat(9,equalTo(6+3));

        assertThat(9, not(3+3));
        assertThat(9, is(not(3+3)));
        assertThat(9,not(equalTo(3+3)));
        assertThat(9,is(not(equalTo(3+3))));

        assertThat(9, is(greaterThan(3+3)));
        assertThat(9, greaterThan(3+3));
        assertThat(5, lessThanOrEqualTo(3+3));
    }

    @Test
    public void testStrings(){
        // so first will be actual result
        //

        String msg = "Never give up!";

        assertThat(msg,is("Never give up!"));
        assertThat(msg,is(equalTo("Never give up!")));
        assertThat(msg,is(equalToIgnoringCase("Never give up!")));

        assertThat(msg,startsWith("Never"));
        assertThat(msg,startsWithIgnoringCase("never"));
        assertThat(msg,endsWith("up!"));
        assertThat(msg,endsWithIgnoringCase("UP!"));

        assertThat(msg,containsString("give"));
        assertThat(msg,containsStringIgnoringCase("Give"));

        assertThat(msg,not("Give up now!"));
        assertThat(msg,is(not("Give up now!")));
    }

    @Test
    public void testCollection(){

        List<Integer> numberList = Arrays.asList(3, 5, 1, 77, 44, 76); // 6 element here

        // how to check size of elements
        assertThat(numberList, hasSize(6));

        // how to check 44 is into collection
        assertThat(numberList, hasItem(44));
        // asserThat(numberList, hasItem(2)); to make it fail

        // how to check 44 and 76 is into collection
        assertThat(numberList, hasItems(44, 76));
        assertThat(numberList, hasItems(44, 76, 1));
        // asserThat(numberList, hasItems(44,76,1,2)); to make it fail

        assertThat(numberList, hasItems(greaterThan(70)));

        assertThat(numberList, everyItem(greaterThan(0)));

        assertThat(numberList,containsInRelativeOrder(3, 5, 1, 77, 44, 76));

    }
}
