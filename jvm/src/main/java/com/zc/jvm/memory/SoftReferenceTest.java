package com.zc.jvm.memory;

import java.lang.ref.SoftReference;

/**
 * JVM最大内存为20M
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        SoftReference<byte[]> s = new SoftReference(new byte[1024 * 1024 * 10]);
        System.out.println(s.get());
        System.gc();

        //再次分配对象时，发现内存空间不够，会回收调s。
        byte[] nb = new byte[1024 * 1024 * 15];
        System.out.println(s.get());
    }
}
