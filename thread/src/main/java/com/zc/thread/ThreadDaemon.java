package com.zc.thread;

/**
 * @author zhangchi
 */
public class ThreadDaemon implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("daemon thread finally is run");
        }
    }
}
class ExecuteDaemon{
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadDaemon(),"DaemonTest");
        thread.setDaemon(true);
        thread.start();
    }
}
