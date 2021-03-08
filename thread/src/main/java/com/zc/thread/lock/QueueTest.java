package com.zc.thread.lock;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueTest {
    static class Nobject{

    }
    public static void main(String[] args) {
        ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();
        clq.add(new Nobject());
    }
}
