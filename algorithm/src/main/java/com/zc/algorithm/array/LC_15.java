package com.zc.algorithm.array;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * @author zhangchi
 */
public class LC_15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        violence(nums).forEach(e -> e.forEach(System.out::print));
        doublePointer(nums).forEach(e -> e.forEach(System.out::print));
    }

    /**
     * 暴力解法 时间复杂度为O(n^3)
     *
     * @param nums
     * @return
     */
    public static Set<List<Integer>> violence(int[] nums) {
        //使用set用于过滤重复集合
        Set<List<Integer>> resSet = new HashSet<>();
        if (nums == null || nums.length < 3) {
            return resSet;
        }
        //对数组进行排序，避免后续循环中还需判断。
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        resSet.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return resSet;
    }

    /**
     * 通过双指针（对撞指针）可以将时间复杂到降到O(n^2)
     * 其核心思路就是将三数之和降级为两数之和来处理
     * @param num
     * @return
     */
    public static Set<List<Integer>> doublePointer(int[] num) {
        Set<List<Integer>> resSet = new HashSet<>();
        if (num == null || num.length < 3) {
            return resSet;
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int sum = num[left] + num[right];
                if (num[i] + sum == 0) {
                    /*
                     * 当循环结果为所要寻找结果时，左指针向左移动，右指针向右移动
                     */
                    resSet.add(Arrays.asList(num[i], num[left], num[right]));
                    left++;
                    right--;
                } else if (num[i] < sum) {
                    //如果当前循环元素小于指针和，左指针向左移动，右指针不动。
                    left++;
                } else {
                    //如果当前循环元素大于指针和，右指针向右移动，左指针不动。
                    right--;
                }
            }
        }

        return resSet;
    }
}