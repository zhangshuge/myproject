package com.zc.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangchi
 */
public class ArraySums {
    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap(len - 1);
        for (int i = 0; i < len; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no two sum solution");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 3};
        int[] indexArray = twoSum(nums, 6);
        System.out.println(indexArray.toString());
    }
}
