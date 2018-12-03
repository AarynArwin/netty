package com.geekerit.netty.daxiangfenxiang;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

/**
 * @ClassName ClientHnadler
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/12/3 14:23
 * @Version 1.0
 */
public class ClientHnadler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端开始读取信息******\n,接收到的数据为--" + msg.toString());

        ctx.channel().attr(AttributeKey.valueOf("testKey")).set(msg.toString());

        ctx.channel().close();
    }
}
