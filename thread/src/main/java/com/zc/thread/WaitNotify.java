package com.zc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangchi
 */
public class WaitNotify {
    static Object objectLock = new Object();
    static boolean flag = true;

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            //加锁，拥有objectLock对象的monitor(监控器)
            synchronized (objectLock) {
                //当条件不满足时继续等待，同事释放了objectLock的锁。
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is ture, wait " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        objectLock.wait();
                        System.out.println("等待后就不输出了，唤醒后继续执行！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + "flag is false, wait " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (objectLock) {
                try {
                    System.out.println(Thread.currentThread() + " hold objectLock. notify " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    objectLock.notify();
                    flag = false;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (objectLock) {
                try {
                    System.out.println(Thread.currentThread() + " hold objectLock. again " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
