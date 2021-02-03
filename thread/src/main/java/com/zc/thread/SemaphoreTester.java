package com.zc.thread;

import java.util.concurrent.Semaphore;

/**
 * @author zhangchi
 */
public class SemaphoreTester {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Thread threadA = new Thread(new PrintString(semaphore,"Thread-A"));
        Thread threadB = new Thread(new PrintString(semaphore,"Thread-B"));
        Thread threadC = new Thread(new PrintString(semaphore,"Thread-C"));
        threadA.start();
        threadB.start();
        threadC.start();
    }
}

class PrintString  extends Thread{
    private Semaphore semaphore;
    private String threadName;

    PrintString(Semaphore semaphore, String threadName) {
        this.semaphore = semaphore;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            super.run();
            semaphore.acquire();//获取信号量许可
            System.out.println(threadName);
            semaphore.release();//释放信号量
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}