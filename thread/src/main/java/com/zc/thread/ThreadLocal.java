package com.zc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangchi
 */
public class ThreadLocal {
    private static final java.lang.ThreadLocal<Long> TIME_THREADLOCAL = new java.lang.ThreadLocal<>() {
        //重新了初始化方法，用于第一次get()时没有set()的情况，每个线程只会调用一次。
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    static java.lang.ThreadLocal<String> tl = new java.lang.ThreadLocal<>();

    private static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        tl.set("tl");
    }

    private static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("clost: " + ThreadLocal.end() + " mills");
        System.out.println(tl.get());;
        Thread thread = Thread.currentThread();
    }
}
