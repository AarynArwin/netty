package com.geekerit.netty.gitchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {

    private final String host;
    private final int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws Exception {
        // 创建线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建引导类Bootstrap，用于引导客户端的启动
            Bootstrap b = new Bootstrap();
            b.group(group)
                    // 指定客户端的IO模型为NIO
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new LineBasedFrameDecoder(1024));
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            // 连接到服务器，阻塞等待直到连接完成
            ChannelFuture future = b.connect(host, port).sync();
            // 阻塞，直到Channel 关闭
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient("127.0.0.1", 6789);
        client.connect();
    }
}
