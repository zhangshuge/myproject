package com.zc.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉查找树——中序遍历（左中右）
 * @author zhangchi
 */
public class LC_94 {
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
    }
    
    public static List brush1_recursive(TreeNode root,List<Integer> list){
        if (root != null){
            brush1_recursive(root.left,list);
            list.add(root.value);
            brush1_recursive(root.right,list);
            return list;
        }
        return null;
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
