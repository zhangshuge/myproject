package com.zc.thread;

/**
 * @author zhangchi
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Domino(previous), "Thread-" + String.valueOf(i));
            previous = thread;
            thread.start();
        }
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : terminate.");
        }
    }
}
