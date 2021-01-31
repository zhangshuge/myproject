package com.zc.thread;

/**
 * @author zhangchi
 */
public class OneObject extends Thread {
    private int count = 5;

    @Override
    public void run() {

        count--;
        System.out.println(Thread.currentThread().getName() + " 执行： " + count);
    }
}

class OneObjectMain {
    public static void main(String[] args) {
        OneObject one = new OneObject();
        Thread t1 = new Thread(one, "t1");
        t1.start();
        Thread t2 = new Thread(one, "t2");
        t2.start();
        Thread t3 = new Thread(one, "t3");
        t3.start();
        Thread t4 = new Thread(one, "t4");
        t4.start();
    }
}