package com.zc.algorithm.array;

/**
 * 滑动窗口
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其和 ≥ target 的长度最小的 连续子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * @author zhangchi
 */
public class LC_209 {
    public static void main(String[] args) {
        int[] num = {2, 3, 1, 2, 4, 3};
        System.out.println(brush1(num, 7));
    }

    public static int brush1(int[] num, int target) {
        int point = 0;//滑动窗口的起始位置指针
        int subLength = 0;//滑动窗口的长度
        int sum = 0;//滑动窗口之和
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {//外层循环的i代表了快指针
            sum += num[i];
            while (sum >= target) {//窗口内的数据大于等于目标值时，移动慢指针也就是缩短滑动窗口继续匹配
                subLength = i - point + 1;
                result = result < subLength ? result : subLength;//取出最小返回结果
                sum -= num[point++];//移动慢指针位置
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
