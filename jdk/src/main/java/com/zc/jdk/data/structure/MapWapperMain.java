package com.zc.jdk.data.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapWapperMain {
    public static void main(String[] args) {
        MapWapper mapWapper = new HashMapWapper(16);
        mapWapper.put("AaAaAaAa", "1");
        mapWapper.put("AaAaAaBB", "2");
        mapWapper.put("AaAaBBAa", "3");
        mapWapper.put("AaAaBBBB", "4");
        mapWapper.put("AaBBAaAa", "5");
        mapWapper.put("AaBBAaBB", "6");
        mapWapper.put("AaBBBBAa", "7");
        mapWapper.put("AaBBBBBB", "8");
        mapWapper.put("BBAaAaAa", "9");
        mapWapper.put("BBAaAaBB", "10");
        mapWapper.put("BBAaBBAa", "11");
        mapWapper.put("BBAaBBBB", "12");
        mapWapper.put("BBBBAaAa", "13");
        mapWapper.put("BBBBAaBB", "14");
        mapWapper.put("BBBBBBAa", "15");
        mapWapper.put("BBBBBBBB", "18");

        System.out.println(HashCUtil.generateN(4).toString());


    }

    static class HashCUtil {

        private static String[] base = new String[]{"Aa", "BB"};

        public static List<String> generateN(int n) {
            if (n <= 0) {
                return null;
            }

            List<String> list = generateOne(null);
            for (int i = 1; i < n; ++i) {
                list = generateOne(list);
            }

            return list;
        }


        public static List<String> generateOne(List<String> strList) {
            if ((null == strList) || (0 == strList.size())) {
                strList = new ArrayList<String>();
                for (int i = 0; i < base.length; ++i) {
                    strList.add(base[i]);
                }

                return strList;
            }

            List<String> result = new ArrayList<String>();

            for (int i = 0; i < base.length; ++i) {
                for (String str : strList) {
                    result.add(base[i] + str);
                }
            }

            return result;
        }
    }

}
