package com.geekerit.netty.client;

import com.geekerit.netty.other.HelloHnadler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/11/30 17:55
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {

        ClientBootstrap clientBootstrap = new ClientBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));

        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("HiHandler",new HiHandler());




                return pipeline;
            }
        });

        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));

        Channel channel = connect.getChannel();

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("请输入信息:");
            channel.write(scanner.next());
        }


    }
}
