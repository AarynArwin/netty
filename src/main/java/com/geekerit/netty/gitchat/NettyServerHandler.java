package com.geekerit.netty.gitchat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 与客户端的连接建立时被调用
     * @param ctx
     * @throws Exception
     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("接收的连接来自 " + ctx.channel());
//        // 发送给客户端的问候
//        String welcome = "Welcome to " + InetAddress.getLocalHost().getHostName() + "!";
//        ctx.writeAndFlush(Unpooled.copiedBuffer(welcome.getBytes()));
//    }

    /**
     * 当有客户端发送数据过来时，channelRead0 方法被调动
     * @param ctx
     * @param msg
     * @throws Exception
     */
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        //String body = msg.toString(CharsetUtil.UTF_8);
//        System.out.println("客户端发送过来的数据 = " + msg);

//        String response = "Did you say '" + body +"'?";
//        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//    }

    /**
     * 发生异常，打印异常信息，关闭连接
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("客户端发送过来的数据 = " + msg);
    }
}
