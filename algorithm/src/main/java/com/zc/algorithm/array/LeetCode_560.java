package com.zc.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 */
public class LeetCode_560 {
    public int violent(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        int count = 0;
        //双重循环
        for (int i = 0; i < len; ++i) {
            for (int j = i; j < len; ++j) {
                sum += nums[j];
                //发现符合条件的区间
                if (sum == k) {
                    count++;
                }
            }
            //记得归零，重新遍历
            sum = 0;
        }
        return count;
    }

    public int preSum(int[] nums, int k) {
        int count = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
                System.out.print("count=" + count + "__");
            } else {
                System.out.print(" 未命中 " + "__");
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
            System.out.println("preSum=" + preSum + "  map:" + map.toString());
        }
        System.out.println("共用" + count + "个和为" + k + "的子集合。");
        return count;
    }


    public static void main(String[] args) {
        LeetCode_560 sum = new LeetCode_560();
        //sum.violent(new int[]{1, 1, 1}, 2);
        sum.preSum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7);
    }
}
