package com.zc.test;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {


    public static void main(String[] args) {

        int n = 4;
        int c = (n - 1);
        System.out.println("每支球队比赛场次：" + c);

        System.out.println("每支球队得分种类：" + 3 * c);

        Map<String, Integer> stringLength = new HashMap<>();
        stringLength.put("John", 5);
        assert ((long)stringLength.computeIfAbsent("John", s -> s.length())) == 4;


    }

    private static int calculate(int n) {
        if (n == 0) {
            return 1;
        }
        return 2 * calculate(n - 1);
    }


}
