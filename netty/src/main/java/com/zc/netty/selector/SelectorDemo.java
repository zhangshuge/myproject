package com.zc.netty.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhangchi
 */
public class SelectorDemo {
    /**
     * 向selector注册事件
     */
    public Selector getSelector() throws IOException {
        //创建Selector对象
        Selector selector = Selector.open();
        //创建可选择通道，并配置为非阻塞模式
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);

        //绑定通道到指定端口
        ServerSocket socket = channel.socket();
        InetSocketAddress address = new InetSocketAddress(8080);
        socket.bind(address);

        //向Selector注册感兴趣的事件
        channel.register(selector, SelectionKey.OP_ACCEPT);

        return selector;
    }

    /**
     * 开始监听
     */
    public void listen() {
        System.out.println("listen on " + 8080);
        Selector selector = null;
        try {
            selector = getSelector();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //死循环，轮训获取监听时间状态
            while (true) {
                //如果没有事件被触发，那么线程会阻塞在select()调用处,直到最少有一个事件被触发，线程才会从该处退出。
                selector.select();
                //线程退出select()的临界区后，再通过selectedKeys获取所有触发时间的集合。
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //从触发时间的key结合中删除该key,即用过之后就清理掉。
                    iterator.remove();
                    //根据触发事件key，调用相应的处理方法
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(SelectionKey key) throws IOException {
        Selector selector = getSelector();
        ByteBuffer buffer = ByteBuffer.allocate(1);
        //接受请求
        if (key.isAcceptable()) {
            //当调用事件被触发时，再将感兴趣的读事件注册到选择器中
            ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = socketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        }
        //读取事件
        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if (len > 0) {
                buffer.flip();
                //读取到数据后，再将感兴趣的写事件注册到选择器中，所要写入的内容就是读取回来的content.
                String content = new String(buffer.array(), 0, len);
                SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_WRITE);
                selectionKey.attach(content);
            } else {
                //读完数据后关闭通道
                channel.close();
            }
            //清理缓冲区
            buffer.clear();
        }
        //接收事件
        if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            ByteBuffer byteBuffer = ByteBuffer.wrap(("对输出内容进行包装：" + content).getBytes());
            if (byteBuffer != null) {
                channel.write(byteBuffer);
            } else {
                channel.close();
            }
        }
    }

}
