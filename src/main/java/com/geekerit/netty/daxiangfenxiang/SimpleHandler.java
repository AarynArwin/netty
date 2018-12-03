package com.geekerit.netty.daxiangfenxiang;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @ClassName SimpleHandler
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/11/30 16:05
 * @Version 1.0
 */
public class SimpleHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        System.out.println("开始读取数据------");
        if (msg instanceof ByteBuf){
//            ByteBuf byteBuf = (ByteBuf) msg;
//
//            String s = byteBuf.toString(Charset.defaultCharset());
//
//            System.out.println("服务端接收信息" + s);
//
//            ctx.channel().writeAndFlush("服务端回复消息~");

        }
    }


}
