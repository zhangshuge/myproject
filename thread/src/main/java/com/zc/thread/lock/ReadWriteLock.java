package com.zc.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangchi
 */
public class ReadWriteLock {
    Map<String,Object> map = new HashMap<>();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    Lock readLock = rwLock.readLock();
    Lock writeLock = rwLock.writeLock();
    public Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
    public void put(String key,Object obj){
        writeLock.lock();
        try {
            map.put(key,obj);
        }finally {
            writeLock.unlock();
        }
    }
}

class Downgrade{
    static ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    static Lock r = rw.readLock();
    static Lock w = rw.writeLock();
    public static void main(String[] args) {
        r.lock();
        r.unlock();
        /*
         * 先要获取读锁，再释放。  这一步主要是为了保证数据的可见性。
         * 如果当前线程不获取读锁，而是直接过去写锁，假设此刻另一个线程T获取了写锁并修改了数据，当前线程在阻塞中是无法感知到的。
         * 而如果当前线程获取了读锁，那么在有读锁未被释放的情况下线程T是不能获取写锁的，会被阻塞。这样做可以保证当前线程操作的数据是最新的。
         */
        w.lock();
        try {

            //修改数据
            r.lock();
        } finally {
            w.unlock();
        }

        try {
            //执行逻辑
        } finally {
            r.unlock();
        }
    }
}
