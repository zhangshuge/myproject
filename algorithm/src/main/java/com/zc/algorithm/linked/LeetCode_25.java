


package com.zc.algorithm.linked;

public class LeetCode_25 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));


    }

    public ListNode Solution1(ListNode listNode, int target) {
        ListNode hair = new ListNode(0);
        ListNode head = listNode;
        //前一个节点，默认先造一个0作为第一个前节点
        ListNode pre = hair;
        while (head != null) {
            //迭代过程中的子节点段
            ListNode tail = pre;
            //按照target向前移动
            for (int i = 0; i < target; i++) {
                tail = head.next;
                if (tail == null){
                    return hair.next;
                }
            }

            //开始反转子节点段

        }
        return hair;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
