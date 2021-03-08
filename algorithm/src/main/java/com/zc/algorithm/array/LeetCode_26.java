package com.zc.algorithm.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author zhangchi
 */
public class LeetCode_26 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int slow = 0;//慢指针,因入参为有序队列，那么第0位肯定是要输出的。从o~slow区域就是所要输出的范围。

        for (int fast = 1; fast < nums.length; fast++) {
            /*
             * 如果快慢指针不相等，代表两数不重复，那么需要慢指针向后移动一位，扩大其目标区域。
             * 同时快指针将不重复的值赋给慢指针
             */
            if (nums[slow]!=nums[fast]){
                slow++;
                nums[slow]=nums[fast];
            }
        }
        System.out.println(slow+1);
        System.out.println(Arrays.toString(nums));


        //使用stream提供的distinct虽然可以去重，但是属于引入了新的数组，不符合题目含义。
        int[] str = Arrays.stream(nums).distinct().toArray();
        System.out.println(Arrays.toString(str));

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[i] = nums[i + 1];
            }
            System.out.println(Arrays.toString(nums));
           /* for (int j = i + 1; j < nums.length; j++) {
                System.out.println(nums[i] + "-" + nums[j]);
                if (nums[i]!=nums[j]){

                }


                System.out.println(Arrays.toString(nums));
            }*/
        }
    }
}
