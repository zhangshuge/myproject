package com.zc.netty.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangchi
 */
public class AIOClient {
    private AsynchronousSocketChannel client;

    public AIOClient() throws Exception {
        //异步连接通道
        client = AsynchronousSocketChannel.open();
    }

    public void connect(String host, int port) {
        //根据主机和端口创建一个连接通道
        client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Object>() {

            /**
             * 连接通道创建成功后调用completed方法向缓冲区写入数据
             * 再通过write返回的Future.get()将缓冲区的数据写入连接通道
             * @param result
             * @param attachment
             */
            @Override
            public void completed(Void result, Object attachment) {
                try {
                    client.write(ByteBuffer.wrap("这是一条测试数据".getBytes())).get();
                    System.out.println("已发送至服务器");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("发送数据失败：" + exc);
            }
        });

        /**
         * 客户端读取数据
         */
        final ByteBuffer bb = ByteBuffer.allocate(1024);
        client.read(bb, null, new CompletionHandler<Integer, Object>() {

            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("I/O操作完成：" + result);
                System.out.println("获取返回结果：" + new String(bb.array()));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new AIOClient().connect("localhost", 8080);
    }
}
