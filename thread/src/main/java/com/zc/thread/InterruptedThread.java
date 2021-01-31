package com.zc.thread;

/**
 * @author zhangchi
 */
public class InterruptedThread extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 1000000; i++) {
                if (isInterrupted()) {
                    System.out.println("thread is stop , I'm is break");
                    throw new InterruptedException();
                }
                System.out.println("i=" + i++);
            }
        } catch (InterruptedException e) {
            System.out.println("execute to catch");
            e.printStackTrace();
        }
    }
}

class RunInterrupted{
    public static void main(String[] args) {
        try {
            InterruptedThread thread = new InterruptedThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
