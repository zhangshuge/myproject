package com.zc.algorithm.linked;

public class LeetCode_206 {
    public static void main(String[] args) {
        com.zc.algorithm.linked.LeetCode_206.ListNode listNode = new com.zc.algorithm.linked.LeetCode_206.ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));
        ListNode result =recursiveReverse(listNode);
        //ListNode result = iteratorReverse(listNode);
        while (result != null) {
            System.out.print(result.val + " → ");
            result = result.next;
        }
    }

    /**
     * 递归反转
     */
    public static ListNode recursiveReverse(ListNode listNode) {
        //终止递归的条件
        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        ListNode node = recursiveReverse(listNode.next);
        listNode.next.next = listNode;
        listNode.next = null;
        return node;
    }

    /**
     * 双指针迭代反转，也叫原地反转
     */
    public static ListNode iteratorReverse(ListNode listNode) {
        //上一个节点
        ListNode prev = null;
        //当前节点
        ListNode current = listNode;
        while (current != null) {

            /*备份与反转*/

            //为了避免反转当前节点时丢失原下一节点引用，先备份下当前节点的下一节点。
            ListNode temp = current.next;
            //开始反转当前节点，将当前节点的next指向prev上一个节点
            current.next = prev;

            /*开始移动current和prev指针*/

            //把当前节点赋值给上一节点，就相当于prev指针向前移动一位
            prev = current;
            //把备份的temp临时节点再赋值给当前节点，即相当于当前节点指针向前移动一位
            current = temp;
        }
        return prev;
    }

    static class ListNode {
        int val;
        com.zc.algorithm.linked.LeetCode_206.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, com.zc.algorithm.linked.LeetCode_206.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
