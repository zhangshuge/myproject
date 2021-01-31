package com.zc.thread;

/**
 * @author zhangchi
 */
public class ThreeObject extends Thread{

    private int count = 5;
    @Override
    public void run(){
        while (count > 0){
            count --;
            System.out.println(Thread.currentThread().getName() + " 执行： " + count);
        }
    }
}

class ThreadObjectMain{
    public static void main(String[] args) {
        ThreeObject one = new ThreeObject();
        Thread t1 = new Thread(one);
        t1.start();
        ThreeObject two = new ThreeObject();
        Thread t2 = new Thread(two);
        t2.start();
        ThreeObject three = new ThreeObject();
        Thread t3 = new Thread(three);
        t3.start();
    }
}
