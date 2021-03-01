package com.zc.algorithm.array;

public class LeetCode_724 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        int preSums = 0 ;
        for (int i=0;i<nums.length;i++){
            preSums += nums[i];
        }
        int leftNums=0;
        for (int i=0;i<nums.length;i++){
            if (leftNums == preSums-leftNums-nums[i]){
                System.out.println(i);
                break;
            }
            leftNums += nums[i];
        }
    }
}
