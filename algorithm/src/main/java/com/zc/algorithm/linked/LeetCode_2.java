package com.zc.algorithm.linked;

/**
 * @author zhangchi
 */
public class LeetCode_2 {
    static class ListNode {
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

    static class Solution {
        ListNode sumNodes = null;
        ListNode tailNodes = null;
        int carry = 0;

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            while (l1 != null || l2 != null) {
                int s1 = l1 != null ? l1.val : 0;
                int s2 = l2 != null ? l2.val : 0;
                int sum = s1 + s2 + carry;
                carry = sum / 10;
                if (sumNodes==null){//第一次初始化两个链表，把tailNodes赋值给sumNodes。
                    sumNodes = tailNodes = new ListNode(sum % 10);
                }else {
                    //每次更新tailNodes.next，sumNodes中的next会持续更新
                    tailNodes.next=new ListNode(sum % 10);
                    tailNodes = tailNodes.next;
                }

                l1 = l1 != null ? l1.next : null;
                l2 = l2 != null ? l2.next : null;
            }
            if (carry>0){
                tailNodes.next = new ListNode(carry);
            }
            return sumNodes;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));
        ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));
        Solution solution = new Solution();
        ListNode sumNode = solution.addTwoNumbers(l1,l2);
        System.out.println(sumNode);
    }
}
