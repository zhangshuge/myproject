package com.zc.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    private static Pattern pattern = Pattern.compile("[ZCGJ1234][A-Z0-9]{13}");

    public static void main(String[] args) {
        Matcher matcher = pattern.matcher("512345607890XX");
        //C1085642000133
        //Z01234567890XX
        System.out.println(matcher.matches());
    }
}

