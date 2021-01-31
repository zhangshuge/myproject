package com.zc.thread;

/**
 * @author zhangchi
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("my runnable is run");
    }
}

class Execute {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println("main is end");
    }
}
