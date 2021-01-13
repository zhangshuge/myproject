package com.zc.jvm.memory;

public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 占用内存，方便观察GC执行效果
     */
    private byte[] bigSize = new byte[2 * _1MB];

    /**
     * -Xlog:gc*:stdout:time,pid,tid,level,tags
     */
    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        // 不在使用引用对象
        objA = null;
        objB = null;

        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}
