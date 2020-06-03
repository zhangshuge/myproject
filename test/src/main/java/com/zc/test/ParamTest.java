package com.zc.test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ParamTest {

    public String rp(String msg){
        return msg + "_test";
    }

    public static void main(String[] args) {
        ParamTest pt = new ParamTest();
        String msg = "haha";
        System.out.println(pt.rp(msg));
        System.out.println(msg);

    }

}
