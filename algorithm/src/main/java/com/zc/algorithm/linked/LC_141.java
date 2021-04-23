package com.zc.algorithm.linked;

/**
 * 环形链表：判断链表是否有环
 * @author zhangchi
 */
public class LC_141 {

    public static void main(String[] args) {
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(4);
        node2.next = node3;
        node3.next = node0;
        node0.next = node4;
        node4.next = node3;
        brush1(node2);
    }

    public static boolean brush1(ListNode node) {
        ListNode slow = node, fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            System.out.println(slow.value + "_" + fast.value);
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
            this.next = null;
        }


    }
}
