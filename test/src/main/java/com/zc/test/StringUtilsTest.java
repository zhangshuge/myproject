package com.zc.test;

import org.apache.commons.codec.binary.StringUtils;

public class StringUtilsTest {

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        byte[] b = {22,23};
        String s = StringUtils.newStringUtf8(b);
        System.out.println(s);


        System.out.println("---" + Integer.toBinaryString(HASH_INCREMENT));
    }
}
