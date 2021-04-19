package com.zc.algorithm.linked;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC_145 {
    public static void main(String[] args) {
        /*
         *         15
         *    9         23
         * 3    12   17   28
         *   8
         */
        TreeNode treeNode = new TreeNode(15,
                new TreeNode(9, new TreeNode(3, null, new TreeNode(8)), new TreeNode(12)),
                new TreeNode(23, new TreeNode(17), new TreeNode(28)));
      
        brush1_iteration(treeNode, new ArrayList<>()).forEach(System.out::println);
    }
    /**
     * 迭代
     */
    public static List brush1_iteration(TreeNode root, List<Integer> list) {

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node!=null){
                list.add(node.value);
                stack.addFirst(node);
                node = node.right;
            }
            node = stack.removeFirst();
            node = node.left;
        }
        return list;
    }


    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }

        TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
