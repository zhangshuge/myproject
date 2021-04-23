package com.zc.algorithm.array;

/**
 * 二分查找：是一种基于比较目标值和数组中间元素的教科书式算法。
 * 1、如果目标值等于中间元素，则找到目标位置。
 * 2、如果目标值小于中间元素，则继续向左侧寻找。
 * 3、如果目标值大于中间元素，则继续向右侧寻找。
 *
 * @author zhangchi
 */
public class LC_704 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(brush1(nums, 9));
    }

    public static int brush1(int[] nums, int target) {
        int left = 0, pivot = 0;
        int right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target) {
                return pivot;
            }
            if (target < nums[pivot]) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        return -1;
    }
}
