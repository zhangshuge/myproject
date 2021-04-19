package com.zc.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 通过bootstrap实现netty client。
 *
 * @author zhangchi
 */
public class ChatClient {
    public ChatClient connect(String host, int prot, final String nickName) {
        //EventLoopGroup包装了Reactor线程模型
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //Bootstrap是一个工厂类，提供了对于client和server端的初始化方法.
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)//客户端使用NioSocketChannel
                    .option(ChannelOption.SO_KEEPALIVE, true)//开始保持连接探活属性
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                        }
                    });
            //发起同步操作
            ChannelFuture channelFuture = bootstrap.connect(host, prot).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭，释放线程资源
            group.shutdownGracefully();
        }
        return this;
    }
}
