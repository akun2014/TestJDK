package com.ownerkaka.testnetty.netty;

import com.ownerkaka.testnetty.netty.handler.SimpleClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author akun
 * @Date 2022/1/1 21:20
 */
@Slf4j
public class SimpleClient {

    @SneakyThrows
    public static void main(String[] args) {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SimpleClientHandler());
                    }
                })
        ;
        log.info("client success");
        ChannelFuture sync = bootstrap.connect("127.0.0.1", 9091).sync();
        sync.channel().closeFuture().sync();
    }
}
