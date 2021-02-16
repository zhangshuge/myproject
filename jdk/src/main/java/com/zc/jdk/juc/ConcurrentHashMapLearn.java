package com.zc.jdk.juc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangchi
 */
public class ConcurrentHashMapLearn {
    static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
    public static void main(String[] args) {
        concurrentHashMap.put("","");
    }
}
