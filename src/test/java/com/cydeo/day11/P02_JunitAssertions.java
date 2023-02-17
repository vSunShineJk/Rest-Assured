package com.cydeo.day11;

import com.cydeo.stringUtils.Palindrome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class P02_JunitAssertions {

    /*
        HARD ASSERT - ASSERT
            - Test Execution will be aborted if the assert condition is not met
            - Rest of execution will stop

            - Use Case --> if you're checking critical functionality of the map
     */

    @Test
    public void hardAsset(){
        assertEquals(10,5+5);
        System.out.println("First assert is DONE");

        assertEquals(9,5+5);
        System.out.println("second assert is DONE");

        assertEquals(10,5+5);
        System.out.println("third assert is DONE");
    }

    /*
        SOFT ASSERTION
            - Test execution will continue till end of the code fragment even if one of them is failed

            ---------------- TestNG - SoftAssert softAsser = new SoftAssert(); -----------
                softAssert.assertEquals()..
                softAssert.assertEquals().
     */

    @DisplayName("JUNIT SOFT ASSERTION IS IMPLEMENTED")
    @Test
    public void softAsset(){
        assertAll("Learning soft Assert",
                () -> assertEquals(10, 5+5),
                () -> assertEquals(9, 5+5),
                () -> assertEquals(10, 5+5)
        );
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------- //


    // @ParameterizedTest
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba", "mike" })
    void palindromes(String candidate) {
        System.out.println(Palindrome.isPalindrome(candidate));
    }


    // @RepeatedTest()
    @RepeatedTest(3)
    @DisplayName("JUNIT SOFT ASSERTION IS IMPLEMENTED")
    @Test
    public void SoftAsset(){
        assertAll("Learning soft Assert",
                () -> assertEquals(10, 5+5),
                () -> assertEquals(10, 5+5),
                () -> assertEquals(10, 5+5)
        );
    }



}
