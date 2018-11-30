package com.geekerit.netty.client;

import org.jboss.netty.channel.*;

/**
 * @ClassName HiHandler
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/11/30 18:06
 * @Version 1.0
 */
public class HiHandler extends SimpleChannelHandler {

    /**
     * 读取信息
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {


        String message = (String) e.getMessage();
        System.out.println("读取信息为--" + message);

        //ChannelBuffer channelBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());

        //ctx.getChannel().write(channelBuffer);

        super.messageReceived(ctx, e);
    }

    /**
     * 异常信息捕获
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
    }

    /**
     * 建立连接
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
    }

    /**
     * 关闭连接（必须是连接已经建立，关闭通道时才会触发）
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
    }

    /**
     * channel关闭时触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
    }
}
