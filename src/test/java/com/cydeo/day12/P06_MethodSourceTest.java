package com.cydeo.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class P06_MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getNames")
    void test1(String KingName){
        System.out.println("KingName = " + KingName);
    }

    public static List<String> getNames(){
        return Arrays.asList("Celebrimbor","Elerond","Thranduil","Galadriel");
    }

    /*
        Can we read data from DataBase
            - Create conn/ run query/ store them and feed Parameterized
     */
}
