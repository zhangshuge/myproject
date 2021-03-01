package com.zc.algorithm.array;

public class LeetCode_88 {
    private static void merge(int[] sum1, int[] sum2) {

        int p1 = 0, p2 = 0, p = 0;
        int m1 = sum1.length;
        int m2 = sum2.length;
        int[] copy_sum = new int[m1 + m2];
        while (p1 < m1 && p2 < m2) {
            //copy_sum[p] = sum1[p1] < sum2[p2] ? sum1[p1++] : sum2[p2++];
            if (sum1[p1] < sum2[p2]) {
                copy_sum[p] = sum1[p1];
                p1++;
            } else {
                copy_sum[p] = sum2[p2];
                p2++;
            }
            p++;
        }
        if (p2 < m2) {
            while (m2 - p2 > 0) {
                copy_sum[p] = sum2[p2];
                p2++;
                p++;
            }
        }
        if (p1 < m1) {
            while (m1 - p1 > 0) {
                copy_sum[p] = sum1[p1];
                p1++;
                p++;
            }
        }
    }

    private static void merge2(int[] nums1, int m, int[] nums2, int n) {
        //获取两个数组有效数据的最后指针
        int p1 = m - 1;
        int p2 = n - 1;
        //设置最终数组指针位置
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        //生息的元素直接copy进去
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        int[] sum1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] sum2 = new int[]{2, 5, 6};
        merge2(sum1, 3, sum2, 3);
    }
}
