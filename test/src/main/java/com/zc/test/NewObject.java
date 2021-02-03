package com.zc.test;

/**
 * @author zhangchi
 */
public class NewObject {
    int num;

    NewObject(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        NewObject obj = new NewObject(1);
    }
}
