package com.zc.thread.lock;

import java.lang.annotation.Retention;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 统一时间只允许两个线程访问：
 * 即通过共享式访问，同步状态status初始值为2，当一个线程获取到资源后status减1，当线程释放资源后status加1.
 *
 * @author zhangchi
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }


    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int releaseCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + releaseCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

}
class TwinsMain {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new TwinsLock();
        for (int i = 0; i < 10; i++) {
            TwinsThread w = new TwinsThread(lock);
            w.setDaemon(true);
            w.start();
        }

        while (true){
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
    }
}

