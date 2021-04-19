package com.zc.algorithm.array;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class LeetCode_53 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};


        /*
         * 使用贪心算法：当前和=当前值 + 之前和
         * 如果之前和<0则丢弃，当前和 = 当前值
         * 用当前值和之前的最大和做比较，取出最大和。
         * 指针       ↓  ↓   ↓   ↓   ↓   ↓   ↓   ↓   ↓
         *          -2  1   -3  4   -1  2   1   -5  4
         * 当前值:   -2  1   -3  4   -1  2   1   -5  4
         * 之前和: null -2    1 -2    4  3   5    6  1
         * 当前和:   -2  1   -2  4    3  5   6    1  5
         * 最大和:   -2  1    1  4    4  5   6    6  6
         */
        int pre = 0;//之前和
        int maxAns = nums[0];//最大和
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(pre, maxAns);
        }
        System.out.println(maxAns);

        /*
         * 动态规划：若前一个元素>0，则将其加入到当前元素。
         * 原   数  组：-2   1   -3   4   -1   2   1   -5   4
         * 修改后的数组：-2   1   -2   4    3   5   6    1   5
         */
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0 ) {
                nums[i] += nums[i-1];
            }
        }
        System.out.println(Arrays.toString(nums) + ":" + Arrays.stream(nums).max().getAsInt());
    }
}
