package com.zc.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangchi
 */
public class SemaphoreTester {
    static Semaphore semaphore = new Semaphore(1);
    static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        Thread t =new PrintString(semaphore);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(t);
        }
        threadPool.shutdown();
    }
}

class PrintString extends Thread {
    private Semaphore semaphore;
    private int num;//非线程安全的变量

    PrintString(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();//
            TimeUnit.SECONDS.sleep(1);
            num++;
            System.out.println(Thread.currentThread().getName() + ":" + num);
            semaphore.release();//释放信号量
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}