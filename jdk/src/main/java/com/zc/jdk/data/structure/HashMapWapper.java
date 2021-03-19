package com.zc.jdk.data.structure;

public class HashMapWapper<k, v>
        extends AbstractMapWapper<k, v>
        implements MapWapper<k, v> {
    /**
     * 负载因子
     */
    final float loadFactor;
    /**
     * 默认负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 默认初始容量大小为16
     */
    static final int DEFULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 最大容量值
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 扩容的临界点
     * 下一个要调整大小的大小值 = 数组容量 * loadFactor
     */
    int threshold;
    /**
     * 包含键值对的数量
     */
    transient int size;

    public HashMapWapper() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMapWapper(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMapWapper(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始容量不能小于0" + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("负载因子不合法"
                    + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * 对于给定的目标容量，返回两倍大小的幂。
     */
    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
