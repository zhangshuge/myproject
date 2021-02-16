package com.zc.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangchi
 */
public class ThreadState {
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class LockCAS implements Runnable {
        Lock lock = new ReentrantLock();

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "timeWaiting").start();
        new Thread(new LockCAS(),"LockCAS").start();
        new Thread(new LockCAS(),"LockCAS-unLock").start();
        new Thread(new Waiting(), "waiting").start();
        new Thread(new Blocked(), "Blocked-lock").start();
        new Thread(new Blocked(), "Blocked-unLock").start();
    }
}
