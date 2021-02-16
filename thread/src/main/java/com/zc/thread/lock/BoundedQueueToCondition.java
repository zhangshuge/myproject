package com.zc.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangchi
 */
public class BoundedQueueToCondition<T> {
    private Object[] items;
    private int count; // 当前队列长度
    private int addIndex; //添加的下标
    private int removeIndex; //删除的下标
    Lock lock = new ReentrantLock();
    Condition fullCondition = lock.newCondition();
    Condition emptyCondition = lock.newCondition();

    public BoundedQueueToCondition(int size) {
        //设置队列大小
        items = new Object[size];
    }

    public void push(T t) {
        lock.lock();
        try {
            /*
             * 表示数组队列已满,当前添加线程释放锁并进入等待状态。
             */
            while (count == items.length) {
                fullCondition.await();
            }
            /*
             * 数组队列没有满的时候添加数组，同时通知等待在emptyCondition上的获取线程，有新数据添加可以获取了。
             */
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            emptyCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T pull() {
        lock.lock();
        try {
            /*
             * 如果数组队列空了，当前获取线程释放锁并进入等待状态。
             */
            while (count == 0) {
                emptyCondition.await();
            }
            Object obj = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            fullCondition.signal();
            return (T) obj;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
