package com.zc.thread.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zhangchi
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        SynchronizedTest st = new SynchronizedTest();
        //无锁
        System.out.println(ClassLayout.parseInstance(st).toPrintable());
        synchronized (st){
            //没有开启偏向锁，直接上轻量级锁
            System.out.println(ClassLayout.parseInstance(st).toPrintable());
        }
    }
}
