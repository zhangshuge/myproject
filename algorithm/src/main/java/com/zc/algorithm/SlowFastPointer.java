package com.zc.algorithm;

import java.util.LinkedList;

/**
 * @author zhangchi
 */
public class SlowFastPointer {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static private int find(ListNode listNode) {
        ListNode slow, fast;
        slow = fast = listNode;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.val;
    }

    static private int find(ListNode listNode, int k) {
        ListNode slow, fast;
        slow = fast = listNode;
        while (k-1 != 0) {//快指针先往前走K-1步，因为这里是把最后一位当作倒数第一位的
            fast = fast.next;
            k--;
        }
        while (fast!=null && fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }

        return slow.val;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        ListNode listNode5 = new ListNode(5);
        listNode4.next = listNode5;
        ListNode listNode6 = new ListNode(6);
        listNode5.next = listNode6;

        System.out.println(find(listNode1));
        System.out.println(find(listNode1,2));

    }

}
