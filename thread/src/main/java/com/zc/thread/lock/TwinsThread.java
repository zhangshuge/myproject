package com.zc.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author zhangchi
 */
public class TwinsThread extends Thread {
    Lock lock = new TwinsLock();

    TwinsThread(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        }
    }
}
