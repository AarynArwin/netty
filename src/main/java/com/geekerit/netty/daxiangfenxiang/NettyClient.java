package com.geekerit.netty.daxiangfenxiang;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;

/**
 * @ClassName NettyClient
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/12/3 14:15
 * @Version 1.0
 */
public class NettyClient {


    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);

            bootstrap.option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
                                                    Delimiters.lineDelimiter()[0]));
                            ch.pipeline().addLast(new StringDecoder());

                            ch.pipeline().addLast(new ClientHnadler());
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    });
            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();

            String msg = "客户端发送信息\r\n";

            future.channel().writeAndFlush(msg);

            //future.channel().writeAndFlush(Delimiters.lineDelimiter()[0]);


            future.channel().closeFuture().sync();

            future.channel().attr(AttributeKey.valueOf("testKey"));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }
}
