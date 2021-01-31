package com.zc.jvm.memory;

/**
 * @author zhangchi
 */
public class Allocation {
    private static final int _1MB = 1024 * 1024;

    /**
     * JVM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     */
    public static void testAllocation2Eden() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        //分配allocation4时会触发一次Minor GC
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3m -XX:+UseSerialGC
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=1  -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 10];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=15  -XX:+PrintTenuringDistribution -XX:TargetSurvivorRatio=80
     */
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3,allocation4;
        allocation1 = new byte[_1MB / 4];
       // allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
    public static void main(String[] args) {
        //  testAllocation2Eden();
        // testPretenureSizeThreshold();
        //testTenuringThreshold();
        testTenuringThreshold2();
    }
}
