package com.ownerkaka.testnetty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @Author akun
 * @Date 2022/1/1 21:47
 */
@Slf4j
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel active {}", ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client channel active ", Charset.defaultCharset()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("server channel read");
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("client response {}", byteBuf.toString(Charset.defaultCharset()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client", Charset.defaultCharset()));
    }
}
