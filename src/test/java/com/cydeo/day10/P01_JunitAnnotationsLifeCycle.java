package com.cydeo.day10;

import org.junit.jupiter.api.*;

public class P01_JunitAnnotationsLifeCycle {

    @BeforeAll
    public static void init(){
        System.out.println("-------------- Before all running");
        System.out.println();
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("--------------- running before each");
    }

    // Ignoring the testCase -- in TestNG @Ignore
    // @Disable  -- also possible to use without message
    @Disabled("This test case is ignored")
    @Test
    public void test1(){
        System.out.println("-------------- Tes1 is running");
    }

    @Test
    public void test2(){
        System.out.println("-------------- Tes2 is running");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("--------------- running after each");
    }

    @AfterAll
    public static void destroy(){
        System.out.println();
        System.out.println("-------------- After all is running");
    }
}
