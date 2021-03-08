package com.zc.netty.aio;

import java.nio.ByteBuffer;

public class BufferSlice {
    public static void main(String[] args) {
        //初始化一个0-9的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i=0;i<buffer.capacity();++i){
            buffer.put((byte) i);
        }

        //创建一个下标索引从3到7的子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();
        //改变子缓冲区的内容
        for (int i=0;i<slice.capacity();++i){
            /*
             * 从子缓冲区里取出来的第0个元素与原缓冲区里的第3个元素是同一个
             * 所以取出原来的值，乘以10后再放回去。
             */
            byte b = slice.get(i);
            b *= 10;
            slice.put(i,b);
        }
        //把缓冲区的位置调整回来，相当于回到初始化的下标。但缓冲区里存放的数据不变。
        buffer.position(0);
        buffer.limit(buffer.capacity());

        //由于子缓冲区和原缓冲区中的数据是共享的，所以从原缓冲区里循环取出的数据应该是包括子缓冲区乘以10以后的元素
        System.out.print("_");
        while (buffer.remaining()>0){
            System.out.print(buffer.get() + "_");
        }
    }
}
