package com.zc.netty.aio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedBuffer {
    static private final int start = 0;
    static private final int size = 1024;

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("/tmp/test.txt", "rw");
        FileChannel fc = raf.getChannel();

        /*
         * 把缓冲区与文件系统进行一个映射关系
         * 只要操作缓冲区里面的内容，文件内容也会跟着改变。
         */
        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE, start, size);
        buffer.put(0, (byte) 97);
        buffer.put(1023, (byte) 122);
        raf.close();
    }
}
