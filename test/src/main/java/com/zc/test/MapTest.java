package com.zc.test;


public class MapTest {


    public static void main(String[] args) {

        int n = 4;
        int c = (n - 1);
        System.out.println("每支球队比赛场次：" + c);

        System.out.println("每支球队得分种类："+ 3*c);


    }
    private static int calculate(int n) {
        if (n == 0){
            return 1;
        }
        return 2 * calculate(n - 1);
    }


}
