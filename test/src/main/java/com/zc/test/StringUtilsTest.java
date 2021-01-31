package com.zc.test;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsTest {

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        String a,b;
        a = "  ";
        b = "324";
        System.out.println(StringUtils.isNoneBlank(a,b));
        System.out.println(StringUtils.isNoneEmpty(a,b));
    }
}
