package com.zc.test;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {

        Map<String, String> map1 = (Map<String, String>) Maps.newHashMap().put("test", "test");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("test", "test");

        Map<String, String> map3 = new HashMap<String, String>() {
            {
                put("test", "test");
            }
        };

        System.out.println(map1.get("test"));
        System.out.println(map2.get("test"));
        System.out.println(map3.get("test"));




    }
}
