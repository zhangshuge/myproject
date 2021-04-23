package com.zc.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉查找树——前序遍历（中左右）
 */
public class LC_144 {
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
        brush1_recursive(treeNode, new ArrayList<>()).forEach(System.out::println);
        System.out.println("-----------------------------");
        brush1_iteration(treeNode, new ArrayList<>()).forEach(System.out::println);
    }


    /**
     * 递归
     */
    public static List brush1_recursive(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.value);
            brush1_recursive(root.left, list);
            brush1_recursive(root.right, list);
            return list;
        }
        return null;
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
                node = node.left;
            }
            node = stack.removeFirst();
            node = node.right;
        }
        return list;
    }


    static class TreeNode {
        int value;
        TreeNode left, right;

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
