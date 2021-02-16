package com.zc.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义同步组件
 *
 * @author zhangchi
 */
public class Mutex implements Lock {
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }


    /**
     * 定义一个内部的静态子类，通过该子类继承QAS，实现其指定的同步状态管理方法。
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 重写isHeldExclusively，用于判断状态是否被占用。当state==1时代表已有其他线程持有锁了。
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 重写tryAcquire，如果state==0代表没有线程持有锁，可以被获取。
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            //如果期望值为0，那么将其更改为1，并将当前线程设置为锁的拥有者
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else {
                return false;
            }
        }

        /**
         * 重写tryRelease释放锁。需要判断当前同步状态。
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            } else {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
        }

        /**
         * 返回一个condition，每个condition都包含了一个condition队列。
         *
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }
}

class ExecuteThread extends Thread {
    private int num = 5;
    Mutex mutex = new Mutex();

    @Override
    public void run() {
        mutex.lock();
        try {
            while (num > 0) {
                num--;
                System.out.println(Thread.currentThread().getName() + " 执行： " + num);
            }
        } finally {
            mutex.unlock();
        }
    }

}

class MutexMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ExecuteThread(), "thread1");
        t1.start();
        Thread t2 = new Thread(new ExecuteThread(), "thread2");
        t2.start();
        Thread t3 = new Thread(new ExecuteThread(), "thread3");
        t3.start();
    }
}
