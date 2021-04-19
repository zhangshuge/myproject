package com.zc.algorithm.array;

import java.util.*;

public class Offer_3 {
    public static void main(String[] args) {
        /*
         * 通过将数组转换成哈希表，即能实现重复数组的查找，也能统计元素重复次数。
         */
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        int[] result = new int[nums.length];
        int index=0;
        for (int i=0;i<nums.length;i++){
            //如果map中存在相同key值，代表重复元素。
            if (map.containsKey(nums[i])){
                result[index] = nums[i];
                index++;
                //记录了每个重复的元素出现的次数。
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }
        System.out.println(Arrays.toString(result));


        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            //利用了set去重的特性，添加失败即代表元素重复。
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        System.out.println(repeat);

    }
}
