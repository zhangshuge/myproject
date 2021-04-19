package com.zc.jdk.data.structure;


import java.io.Serializable;
import java.util.Set;

/**
 * 自定义hashMap，实现核心方法get put resize.
 *
 * @author zhangchi
 */
public class HashMapWapper<k, v>
        extends AbstractMapWapper<k, v>
        implements MapWapper<k, v>, Cloneable, Serializable {
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
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

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
     * 节点数组用于存放大部分数据，除hashCode重复部分。
     */
    transient Node<k, v>[] table;
    /**
     * 用于记录Map修改的次数
     */
    transient int modCount;

    /**
     * 包含键值对的数量
     */
    transient int size;

    /**
     * 链表长度
     */
    static final int TREEIFY_THRESHOLD = 8;
    /**
     * 红黑树的最小节点容量
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    @Override
    public int size() {
        return size;
    }

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
     * 对于给定的目标容量，总是返回2的n次幂。
     */
    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 向视图中放入数据，如果key已存在即覆盖原值。返回覆盖前的值。
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public v put(k key, v value) {
        return putVal(hash(key), key, value, false, true);
    }

    @Override
    public v get(Object key) {//重新父类的get方法
        Node<k, v> e = getNode(hash(key), key);
        return e == null ? null : e.value;
    }

    final Node<k, v> getNode(int hash, Object key) {
        Node<k, v>[] tab = table;//当前视图
        Node<k, v> first;//根据hash取模后的数据可能是一个链表，first代表链表的起始节点。
        Node<k, v> e;
        int n;
        k k1;
        if (tab != null && (n = tab.length) > 0  //判断视图不为空
                && (first = tab[(n - 1) & hash]) != null) { //同时根据hashCode取模运算得到的Node不为null
            if (first.hash == hash //当hash值相等并不代表是我们所要查询的值，还需要比较key是否完全相同
                    && ((k1 = first.key) == key || (key != null && key.equals(k1)))) {
                return first;
            }
            /*
             * 当发生hash碰撞时，first的头结点肯定会有next节点，顺着链表向下寻找。
             */
            if ((e = first.next) != null) {
                //从红黑树结构中获取
                if (first instanceof TreeNode) {

                }
                do {
                    if (e.hash == hash && ((k1 = e.key) == key || (key != null && key.equals(k1)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }


        return null;
    }


    static final int hash(Object key) {
        int h;//第一步：取key的hashCode值
        // 第二步：h >>> 16高位参与计算
        // 第三步：取模运算，JDK8以后是通过hashCode的高16位 异或 低16位实现，提升了取模运算的效率。
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final v putVal(int hash, k key, v value, boolean onlyIfAbsent, boolean evict) {

        Node<k, v>[] tab;
        Node<k, v> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0) {//初始化大小
            n = (tab = resize()).length;
        }
        //如果计算出下标的节点为null，说明没有出现重复的key，不存在值覆盖的情况。直接创建node赋值即可。
        if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = newNode(hash, key, value, null);
        } else {
            Node<k, v> e;
            k k1;
            //当k的hashCode相等，并且原有key和新保存的key相等，且key不能等于null.
            // 此时代表key重复，直接用新值覆盖旧址即可。
            if (p.hash == hash &&
                    ((k1 = p.key) == key || (key != null && key.equals(k1)))) {
                e = p;
            }
            /*
             * 处理hash碰撞的两种情况
             */
            else if (p instanceof TreeNode) {//hashCode碰撞超过8次，则通过红黑树保存。
                e = null;
            } else {//hashCode碰撞小于等于8次，通过链表存储。

                for (int binCount = 0; ; ++binCount) {//通过死循环的方式，查找链表最后一个节点，拼接新增节点。
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) {
                            //TODO 转换为红黑树
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    //当链表上有相同key时，直接替换
                    if (e.hash == hash &&
                            ((k1 = e.key) == key || (key != null && key.equals(k1)))) {
                        break;
                    }
                    p = e;
                }
            }

            /*
             * 重新映射新老value
             */
            if (e != null) {
                v oldValue = e.value;//记录替换前的value
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;//更新成替换后的value
                }
                afterNodeAccess(e);
                return oldValue;//当有键值重复或者hashCode碰撞时，返回替换前的值。
            }
        }

        ++modCount;//累加修改次数
        if (++size > threshold) {//如果当前容量大于临界值了，需要扩容。
            resize();
        }
        afterNodeInsertion(evict);//这个是给linkedHashMap留的回调函数。
        return null;//只有在用相同key发生value覆盖的时候才返回原有的value值，新的key插入时返回null。
    }

    Node<k, v> newNode(int hash, k key, v value, Node<k, v> next) {
        return new Node<>(hash, key, value, next);
    }

    final Node<k, v>[] resize() {
        /*
         * 记录扩容前数据
         */
        Node<k, v>[] oldTab = table;//扩容前视图
        int oldCap = oldTab == null ? 0 : oldTab.length;//扩容前的视图大小
        int oldThr = threshold;//扩容前的临界点大小

        /*
         * 扩容后的新值
         */
        int newCap, newThr = 0;

        if (oldCap > 0) {//代表原视图中有值，不是初始化。
            /*
             * 如果扩容前的视图已经为最大容量，那么扩容后的值应该为Integer的最大者。
             * 也就是MAXIMUM_CAPACITY的2倍
             * MAXIMUM_CAPACITY  = 1073741824
             * Integer.MAX_VALUE = 2147483647
             */
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
                /*
                 * 如果扩容前的视图超过默认大小，并且扩容2倍后还小于最大容量范围
                 * 那么直接扩大新视图的临界点。
                 */
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1;//在现有大小的基础上扩2倍
            }
        } else if (oldThr > 0) {//初始化，并且设置了初始化阈值大小
            newCap = oldThr;
        } else {//初始化，没有设置阈值大小，此时使用默认容量范围
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }

        if (newThr == 0) {//如果设置了初始化容量，则还需计算其扩容临界点
            float ft = (float) newCap * loadFactor;
            //如果初始容量小于最大值，那么直接返回ft.如果出事容量大于最大值则直接将扩容边界设置到最大容量，Integer.MAX_VALUE
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY
                    ? (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;//更新扩容后的视图临界点

        /*
         * 计算完扩容边界的临界点和扩容后的数组大小之后，就可以开始创建节点数组了.
         */
        Node<k, v>[] newTab = (Node<k, v>[]) new Node[newCap];
        table = newTab;//将扩容后的新视图赋值给扩容前的视图，现在table里面是键值都为空了。
        if (oldTab != null) {//oldTab里存放着扩容前视图的所有键值
            for (int j = 0; j < oldCap; ++j) {//todo 这里为什么是++j呢
                Node<k, v> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null) {//代表没有发生过hashCode碰撞
                        //计算存放下标位置，用节点的hash值和视图容量做&运算。
                        newTab[e.hash & (newCap - 1)] = e;
                    } else if (e instanceof TreeNode) {//判断节点是否为树形节点

                    } else {//链表形式保存数据

                    }
                }
            }
        }


        return newTab;
    }

    /**
     * 红黑树存储
     *
     * @param tab
     * @param hash
     */
    final void treeifyBin(Node<k, v>[] tab, int hash) {
        int n;//视图长度
        int index;//根据数组容量和key的hashCode取模后的下标
        Node<k, v> e;//根据下标获取到所要操作的节点
        //如果过整个链表的容量不足默认的64，那么是不会转变为红黑树的。直接扩容就好了，避免hash碰撞的概率。
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) {
            resize();
        } else if ((e = tab[index = (n - 1) & hash]) != null) {
            TreeNode<k, v> hd = null;
            TreeNode<k, v> tl = null;
            do {
                TreeNode<k,v> p = replacementTreeNode(e,null);
                if (tl==null){
                    hd=p;
                }else{
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null){
                hd.treeify(tab);
            }
        }
    }



    /**
     * 存放真实数据的节点
     *
     * @param <k>
     * @param <v>
     */
    static class Node<k, v> implements MapWapper.Entry<k, v> {
        final int hash;
        final k key;
        v value;
        Node<k, v> next;

        Node(int hash, k key, v value, Node<k, v> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public k getKey() {
            return null;
        }

        @Override
        public v getValue() {
            return null;
        }

        @Override
        public v setValue(v value) {
            return null;
        }
    }

    TreeNode<k,v> replacementTreeNode(Node<k,v> p,Node<k,v> next){
        return new TreeNode<>(p.hash,p.key,p.value,next);
    }

    static final class TreeNode<k, v> extends LinkedHashMapWapper.Entry<k, v> {
        TreeNode<k,v> parent;//红黑树链表的根节点
        TreeNode<k,v> left;//左子节点
        TreeNode<k,v> right;//右子节点
        TreeNode<k,v> prev;//上一节点

        TreeNode(int hash, k key, v value, Node<k, v> next) {
            super(hash, key, value, next);
        }

        /**
         * 将链表变为红黑树
         * @param tab
         */
        final void treeify(Node<k,v>[] tab){
            TreeNode<k,v> root = null;//根节点

        }


        @Override
        public k getKey() {
            return null;
        }

        @Override
        public v getValue() {
            return null;
        }

        @Override
        public v setValue(v value) {
            return null;
        }
    }

    @Override
    public Set<Entry<k, v>> entrySet() {
        return null;
    }

    //允许LinkedHashMap后处理的回调
    void afterNodeAccess(HashMapWapper.Node<k, v> p) {
    }

    void afterNodeInsertion(boolean evict) {
    }
}
