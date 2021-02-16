package com.zc.jvm.memory;

import java.util.concurrent.CountDownLatch;

/**
 * 有意义的变量x前后各加7个无意义的变量用来站位，每个变量8个字节，arr[0]的前56位都是无用数据，后8位是x。arr[1]的后56位都是无用数据，前8位是x。
 * 通过这种站位方式保证arr[0]和arr[1]之间没有内存交集，这样分别操作两个数组的线程的内存行中只有自己的x和占位数据，省去了优于缓存一致性需要跨线程
 * 同步内存的开销，提升了处理性能。属于用空间换时间的一种方式。
 *
 * @author zhangchi
 */
public class CacheLinePadding {
    public static long COUNT=10_0000_0000L;
    static class T{
        public long p1,p2,p3,p4,p5,p6,p7;
        public long x = 0L;
        public long p8,p9,p10,p11,p12,p13,p14;
    }
    public static T[] arr= new T[2];
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
           for (long i=0;i<COUNT;i++){
               arr[0].x =i;
           }
           cdl.countDown();
        });
        Thread t2 = new Thread(()->{
           for (long i=0;i<COUNT;i++){
               arr[1].x =i;
           }
           cdl.countDown();
        });
    }
}
