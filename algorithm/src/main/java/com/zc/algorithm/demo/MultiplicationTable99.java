package com.zc.algorithm.demo;

/**
 * 实现99乘法表
 *
 * @author zhangchi
 */
public class MultiplicationTable99 {
    static int i = 1, j = 1;

    public static void main(String[] args) {
        // iterable();
        recursion(i);
    }

    public static void iterable() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " * " + i + " = " + i * j + "  ");
            }
            System.out.println();
        }
    }

    public static void recursion(int i) {
        if (i < 10) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " * " + i + " = " + i * j + "  ");
            }
            System.out.println();
            i++;
            recursion(i);
        }

        return;
    }
}
