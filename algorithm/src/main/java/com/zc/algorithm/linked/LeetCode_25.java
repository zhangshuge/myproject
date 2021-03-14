


package com.zc.algorithm.linked;

public class LeetCode_25 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));


    }

    public void Solution1(ListNode listNode, int target) {
        int index = 0;
        ListNode resultNode = new ListNode();

        ListNode tempNode = new ListNode();
        while (listNode.next != null) {
            //临时节点发转并赋值给resultNode，反转后需要归零index
            if (index == target) {
                while (tempNode!=null){
                    resultNode=tempNode.next;
                }
                index = 0;
            } else {//不想等的时候继续累积节点
                index += 1;
                tempNode=listNode;//把当前节点赋给临时节点
            }
        }

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
