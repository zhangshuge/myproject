package com.zc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangchi
 */
public class SingleThread {
    static Thread a = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " A");
        }
    }, "Thread-A");
    static Thread b = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " B");
        }
    }, "Thread-B");
    static Thread c = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " C");
        }
    }, "Thread-C");
    //利用newSingleThreadExecutor()单线程执行FIFO队列的特性实现效果
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            executorService.submit(a);
            executorService.submit(b);
            executorService.submit(c);
        }
    }
}
