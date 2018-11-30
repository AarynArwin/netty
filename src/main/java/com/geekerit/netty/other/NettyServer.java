package com.geekerit.netty.other;



import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NettyServer
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/11/30 16:30
 * @Version 1.0
 */
public class NettyServer {


    public static void main(String[] args) {


        ServerBootstrap serverBootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));

        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("HelloHandler",new HelloHnadler());




                return pipeline;
            }
        });
        serverBootstrap.bind(new InetSocketAddress(8080));
        System.out.println("服务启动!");
    }




}
