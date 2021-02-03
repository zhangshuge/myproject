package com.zc.algorithm.tree;

import com.sun.source.tree.IfTree;

import java.util.Stack;

/**
 * @author zhangchi
 */
public class PrintBinaryTree {
    /**
     * 初始化二叉树结构
     */
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public TreeNode init() {
        TreeNode node15 = new TreeNode(15);
        TreeNode node8 = new TreeNode(8); // 节点15下的左节点8
        node15.left = node8;

        TreeNode node3 = new TreeNode(3); // 节点8下的左节点3
        node8.left = node3;

        TreeNode node1 = new TreeNode(1); // 节点3下的左节点1
        node3.left = node1;

        TreeNode node4 = new TreeNode(4); // 节点3下的右节点4
        node3.right = node4;

        TreeNode node6 = new TreeNode(6); // 节点8下的右节点6
        node8.right = node6;

        TreeNode node5 = new TreeNode(5); // 节点6下的左节点5
        node6.left = node5;

        TreeNode node7 = new TreeNode(7); // 节点6下的右节点7
        node6.right = node7;

        TreeNode node24 = new TreeNode(24); // 节点15下的右节点24
        node15.right = node24;

        TreeNode node20 = new TreeNode(20); // 节点24下的左节点20
        node24.left = node20;

        TreeNode node18 = new TreeNode(18); // 节点20下的左节点18
        node20.left = node18;

        TreeNode node21 = new TreeNode(21); // 节点20下的右节点21
        node20.right = node21;

        TreeNode node30 = new TreeNode(30); // 节点24下的右节点30
        node24.right = node30;

        TreeNode node28 = new TreeNode(28); // 节点30下的左节点28
        node30.left = node28;

        TreeNode node35 = new TreeNode(35); // 节点30下的右节点35
        node30.right = node35;

        // 返回根节点
        return node15;
    }

    public void printTree(TreeNode treeNode) {
        Stack<TreeNode> oddStack = new Stack<>();//奇数粘
        Stack<TreeNode> evenStack = new Stack<>();//偶数栈
        int line = 1;//标识第一行

        oddStack.push(treeNode);//根节点算奇数行，先把跟进点存入奇数栈
        TreeNode tempTreeNode = null;
        while (!oddStack.isEmpty() || !evenStack.isEmpty()) {
            if (line % 2 == 1) {//奇数行
                while (!oddStack.isEmpty()) {
                    tempTreeNode = oddStack.pop();//奇数栈的栈顶数据出栈。
                    System.out.print(tempTreeNode.value + ",");
                    if (tempTreeNode.left != null) {
                        evenStack.push(tempTreeNode.left);//偶数行左子节点先进栈
                    }
                    if (tempTreeNode.right != null) {
                        evenStack.push(tempTreeNode.right);//偶数行右子节点进栈
                    }
                }
            } else {
                while (!evenStack.isEmpty()) {
                    tempTreeNode = evenStack.pop();//偶数栈的栈顶数据出栈
                    System.out.print(tempTreeNode.value + ",");
                }
                if (tempTreeNode.left != null) {
                    oddStack.push(tempTreeNode.left);//奇数行左子节点进栈
                }
                if (tempTreeNode.right != null) {
                    oddStack.push(tempTreeNode.right);//奇数行右子节点进栈
                }
            }
            line++;//更新二叉树行号
        }
    }

    public static void main(String[] args) {
        PrintBinaryTree printBinaryTree = new PrintBinaryTree();
        //初始化二叉树
        TreeNode treeNode = printBinaryTree.init();
        printBinaryTree.printTree(treeNode);
    }
}
