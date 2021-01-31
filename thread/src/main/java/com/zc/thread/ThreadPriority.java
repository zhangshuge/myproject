package com.zc.thread;

/**
 * @author zhangchi
 */
public class ThreadPriority {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setPriority(9);
        threadA.start();
    }

}

class ThreadA extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("thread A has a priority of " + this.getPriority());
        ThreadB threadB = new ThreadB();
        threadB.start();
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("thread B has a priority of " + this.getPriority());
    }
}
