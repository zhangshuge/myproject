package com.zc.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 */
public class LeetCode_974 {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 0, -2, -3, 1};
        int sum = 0;
        int ans = 0;
        int k = 5;//除数

        /*
         *
         */




        //
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];//记录前缀和
            int mod = (sum % k + k) % k;
            int same = map.getOrDefault(mod,0);
            ans += same;
            map.put(mod,same+1);
        }
        System.out.println(ans);
        System.out.println(4 % 5);
        System.out.println(9 % 5);
    }
}

