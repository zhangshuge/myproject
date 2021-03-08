package com.zc.algorithm.charsequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode_3 {
    public static void main(String[] args) {
        String str = "abcabcbb";
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = str.length();
       /* // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
*/
        Set<Character> sc = new HashSet<>();
        String s = "dvdf";


        int nn = s.length();
        sc.add(s.charAt(0));
        int left = 0;
        int right = 1;
        int ls = 0;
        for (int i = 0; i < nn; i++) {
            if (i!=0 && sc.contains(s.charAt(i))){
                i=right+1;
               // sc.clear();
                if (right<nn){
                    sc.add(s.charAt(right));
                }
            }
            //右指针移动
            if (right<i){
                right = i;
            }
            while (right<nn && !sc.contains(s.charAt(right))){
                sc.add(s.charAt(right));
                right++;
            }
            ls = Math.max(ls,sc.size());
        }
        System.out.println(ls);

       /* String str = "abcabcbb";
        Set<Character> set = new HashSet<>();
        int n = str.length();
        int left =0; //滑动窗口的左边界指针
        int right =1; //滑动窗口的右边届指针

        set.add(str.charAt(0));//第一个元素要先放入散列表中
        for (int i=0;i<n;i++){
            while (right<n && !set.contains(str.charAt(right))){
                set.add(str.charAt(right));
                right++;
            }
        }
        System.out.println(set.toString());
*/


       /* Map<String,Integer> map = new HashMap<>(16);
        int i=0;
        int j =1;
        while (j<str.length()){
           if (str.charAt(i)!=str.charAt(j)){
               String key = String.valueOf(str.charAt(i) )+String.valueOf(str.charAt(j));
               Integer value = map.getOrDefault(key,0);
               map.put(key,value);
               j++;
           }
        }*/
        /*for (int j=1;j<str.length();j++){

            System.out.println(str.charAt(i));
        }*/
    }
}
