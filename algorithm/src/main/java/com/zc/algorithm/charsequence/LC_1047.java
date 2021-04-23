package com.zc.algorithm.charsequence;

import java.util.Stack;

/**
 * 删除字符串中的所有相邻重复项
 *
 * @author zhangchi
 */
public class LC_1047 {
    public static void main(String[] args) {
        System.out.println(brush1_stack("abbaca"));
    }

    public static String brush1_stack(String str) {
        Stack stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            char v = str.charAt(i);
            if (!stack.empty() && stack.peek().equals(v)) {
                stack.pop();
            } else {
                stack.push(v);
            }
        }
        String res = "";
        Object[] list = stack.toArray();
        for (int i = 0; i < list.length; i++) {
            res += list[i];
        }
        return res;
    }

}
