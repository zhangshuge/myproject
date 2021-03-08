package com.zc.jdk.juc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhangchi
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(3);
        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Customer(cdl, "张三"));
        threadList.add(new Customer(cdl, "李四"));
        threadList.add(new Customer(cdl, "王五"));
        for (Thread thread : threadList) {
            thread.start();
        }
        new Waiter(cdl, "服务员").start();
    }

    static class Waiter extends Thread {
        CountDownLatch cdl;
        String name;

        Waiter(CountDownLatch cdl, String name) {
            this.cdl = cdl;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                System.out.println(sdf.format(new Date()) + " " + name + "等待顾客");
                cdl.await();
                System.out.println(sdf.format(new Date()) + " " + name + "开始上菜");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Customer extends Thread {
        CountDownLatch cdl;
        String name;

        Customer(CountDownLatch cdl, String name) {
            this.cdl = cdl;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                Random random = new Random();
                System.out.println(sdf.format(new Date()) + " " + name + "出发去饭店");
                Thread.sleep((long) (random.nextDouble() * 3000) + 1000);
                System.out.println(sdf.format(new Date()) + " " + name + "到了饭店");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                cdl.countDown();
            }
        }
    }
}
