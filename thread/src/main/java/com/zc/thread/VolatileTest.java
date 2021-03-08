package com.zc.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {
    private static int num = 0;
    Object object ;
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("修改num");
                num = 3;
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("释放锁");
                lock.unlock();
            }

        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            System.out.println("获取num："+num);
        }).start();
    }
}
