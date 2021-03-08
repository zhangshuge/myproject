package com.zc.netty.aio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {
    public static void main(String[] args) {
        try {
            //创建文件输入流
            FileInputStream inputStream = new FileInputStream("/tmp/test.txt");
            //创建文件操作管道
            FileChannel channel = inputStream.getChannel();
            //分配一个大小为10的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            out("初始化容量大小为的buffer：", buffer);

            //step1: read
            channel.read(buffer);
            out("read() 从文件通道channel中读取数据流，写入缓冲区", buffer);

            /*
             * step2：准备操作之前，要先锁定操作范围。实际上是在调整limit和position的索引位置
             * 将position调至0，可以保证在后面的读取操作是从0开始，即从缓冲区的第一个自己开始。
             * 将limit调至为4，可以保证所读取到的数据范围正好是之前从通道里获取后写入缓冲区的数据
             * 这两个状态索引的变动就是为了确定后续操作的范围
             */
            buffer.flip();
            out("flit() 锁定缓冲区可操作范围：", buffer);

            //step3：循环输出字节
            System.out.println("-------------------------------------");
            while (buffer.remaining() > 0) {
                System.out.println("按字节读取：" + (char) buffer.get());
                out("跟踪每一次字节读取后的缓冲区状态变化", buffer);
            }
            System.out.println("-------------------------------------");
            out("get() 读取完成：", buffer);

            //step4：操作完成后清理缓冲区，可以理解为解锁。
            buffer.clear();
            out("clear() 清理缓冲区：", buffer);

            //最后关闭通道
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void out(String message, ByteBuffer buffer) {
        System.out.println(message);
        System.out.print("position:" + buffer.position() + ", ");
        System.out.print("limit:" + buffer.limit() + ", ");
        System.out.println("capacity:" + buffer.capacity() + ", ");
        System.out.println();
        System.out.println();
    }
}
