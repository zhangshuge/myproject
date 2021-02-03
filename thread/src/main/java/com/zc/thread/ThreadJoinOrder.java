package com.zc.thread;

/**
 * @author zhangchi
 */
public class ThreadJoinOrder {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Athread(), "A");
        Thread b = new Thread(new Bthread(), "B");
        Thread c = new Thread(new Cthread(), "C");

        for (int i = 0; i < 3; i++) {
            a.start();
            a.join();
            b.start();
            b.join();
            c.start();
        }
    }
}

class Athread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " : A");
    }
}

class Bthread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " : B");

    }
}

class Cthread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " : C");
    }
}
