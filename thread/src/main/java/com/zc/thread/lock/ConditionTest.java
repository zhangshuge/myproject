package com.zc.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangchi
 */
public class ConditionTest {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int i = 1;

    public void printA() {
        lock.lock();
        try {
            while (i != 1) {
                condition1.await();
            }

            System.out.println(Thread.currentThread().getName() + " : A");

            i = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (i != 2) {
                condition2.await();
            }

            System.out.println(Thread.currentThread().getName() + " : B");

            i = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (i != 3) {
                condition3.await();
            }

            System.out.println(Thread.currentThread().getName() + " : C");

            i = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                conditionTest.printA();
            }
        }, "Thread-A").start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                conditionTest.printB();
            }
        }, "Thread-B").start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                conditionTest.printC();
            }
        }, "Thread-C").start();
    }
}

