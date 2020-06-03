package com.zc.test;

import java.util.HashMap;
import java.util.Map;

public class MapConcurrenceTest {

    private static Map<String,String> test1(Map<String,String> map) throws InterruptedException {
        map.put("c","c");
        if (map.get("sleep")!= null){
        Thread.sleep(10000);
        }
        return map;
    }

    static class A {
        public static void main(String[] args) throws InterruptedException{
            Map<String,String> map = new HashMap<String, String>();
            map.put("a","a");
            map.put("sleep","ssss");
            map = test1(map);
            System.out.println(map.values());
        }
    }
    static class B {
        public static void main(String[] args) throws InterruptedException{
            Map<String,String> map = new HashMap<String, String>();
            map.put("b","b");
            map = test1(map);
            System.out.println(map.values());
        }
    }

}
