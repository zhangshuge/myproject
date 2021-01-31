package com.zc.algorithm;

import java.util.Scanner;

/**
 * @author zhangchi
 */
public class Factorial {
    public static void main(String[] args) {
        /*System.out.printf("输入任意一个数：");
        Scanner input = new Scanner(System.in);  //用于接收输入数据
        int n = input.nextInt();                 //将输入的数据赋给 n
        int a = 1;                               //用于存储阶乘的值
        for (int i = 1; i <= n; i++) {
            a *= i;                              // 等同于 a = a*i; ---->阶乘运算公式
            System.out.printf("%d\n", a);         //每计算一次就打印一次
        }
        System.out.printf("%d的阶乘为：%d\n", n, a); //打印最后计算的结果*/


        int n = 10;
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = i + j;
            }
        }
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }

    private  void doubleN() {


    }
}
