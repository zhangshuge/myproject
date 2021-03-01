package com.zc.algorithm.queue;

public class LeetCode_622 {

    //单链表法
    class Node {
        public int value;
        public Node nextNode;

        public Node(int value) {
            this.value = value;
            this.nextNode = null;
        }
    }

    private Node header, tail;//定义首尾节点
    private int count;//队列真实长度
    private int capacity;//队列的初始大小，在这里就是指最大长度。

    LeetCode_622(int k) {
        this.capacity = k;
    }
    public boolean enQueue(int value) {
        if (this.count == this.capacity){
            return false;
        }

        Node newNode = new Node(value);
        //初始值，首尾节点均为当前节点。
        if (this.count == 0) {
            header = tail = newNode;
            header.nextNode = tail;
            tail.nextNode = header;
        } else {
            //将当前节点赋值给尾节点的下一个节点
            tail.nextNode = newNode;
            //将当前节点设置位尾节点
            tail = newNode;
            //修改尾节点的下一个节点位头节点，时链表闭环。
            tail.nextNode = header;
        }
        this.count += 1;
        return true;
    }

    public boolean deQueue() {
        if (this.count == 0) {
            return false;
        }
        this.header = this.header.nextNode;
        this.count -= 1;
        return true;
    }
    public int Front() {
        if (this.count == 0) {
            return -1;
        } else {
            return this.header.value;
        }

    }

}
