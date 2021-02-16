package com.zc.algorithm.array;

/**
 * 前缀和
 *
 * @author zhangchi
 */
public class Presum {
    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{1,7,3,6,5,6}));
    }


    public static int pivotIndex(int[] nums) {
        int presum = 0;
        for (int x : nums) {
            presum += x;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == presum - nums[i] - leftSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}
