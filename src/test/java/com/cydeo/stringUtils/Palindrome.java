package com.cydeo.stringUtils;

public class Palindrome {
    public static String isPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        if(sb.reverse().toString().equals(str)) return str + " is palindrome";
        else return str + " isn't palindrome";
    }
}
