package com.zc.thread;

/**
 * @author zhangchi
 */
public class SuspendAndResume extends Thread {
    private long num = 0;

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            num++;
        }
    }
}

class ExecuteSpAndRe {
    public static void main(String[] args) {
        try {
            SuspendAndResume sar = new SuspendAndResume();
            sar.start();
            Thread.sleep(2000);
            sar.suspend();
            System.out.println("Step1 :" + System.currentTimeMillis() + " i=" + sar.getNum());
            Thread.sleep(2000);
            System.out.println("Step1 :" + System.currentTimeMillis() + " i=" + sar.getNum());
            sar.resume();
            Thread.sleep(2000);
            System.out.println("Step2 :" + System.currentTimeMillis() + " i=" + sar.getNum());
        } catch (InterruptedException e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}