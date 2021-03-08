package com.zc.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode_1 {
    public static int[] getTarget(int[] nums, int target) {

        //key为数组元素，value为下标值
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int cn = target - nums[i];
            if (map.containsKey(cn)) {
                return new int[]{map.get(cn), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(getTarget(nums, target)));
    }
}
