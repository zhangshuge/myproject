package com.zc.algorithm.linked;

import java.util.Stack;

public class LeetCode_6 {
    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(3);
        ListNode ln3 = new ListNode(2);
        ln1.next = ln2;
        ln2.next = ln3;

        Stack<Integer> stack = new Stack();
        while (ln1 != null) {
            stack.push(ln1.val);
            ln1 = ln1.next;
        }
        int[] result = new int[stack.size()];
        for(int i=0;i<result.length;i++){
            result[i] = stack.pop();
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
