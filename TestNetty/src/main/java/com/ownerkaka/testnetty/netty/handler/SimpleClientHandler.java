package com.ownerkaka.testnetty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;
import java.nio.charset.Charset;

/**
 * @Author akun
 * @Date 2022/1/1 21:47
 */
@Slf4j
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client {}", ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server", Charset.defaultCharset()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("client channel read");
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("server response {}", byteBuf.toString(Charset.defaultCharset()));
        log.info("server address {}", ctx.channel().remoteAddress());
    }


}
