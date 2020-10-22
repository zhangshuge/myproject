package com.zc.jvm.memory;

/**
 * @author zhangchi
 */
public class DynamicLinking {
    int num = 10;
    public void methodA(){
        System.out.println("methodA()....");
    }
    public void methodB(){
        System.out.println("methodB()....");
        methodA();
        num++;
    }
}
