package com.ownerkaka.testnetty.netty;

import com.ownerkaka.testnetty.netty.handler.SubscribeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author akun
 * @Date 2021/8/21 19:48
 */
@Slf4j
public class NettyServerTest {


    @Test
    public void serverStart() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));
        NioEventLoopGroup worker = new NioEventLoopGroup(10, new DefaultThreadFactory("NettyServerWorker", true));

        try {
            log.info("server starting...");
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    .childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(
                                            new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader()))
                                    )
                                    .addLast(new ObjectEncoder())
                                    .addLast(new IdleStateHandler(0, 0, 100, TimeUnit.MILLISECONDS))
                                    .addLast(new SubscribeServerHandler());
                        }
                    }).validate();
            ChannelFuture sync = bootstrap.bind(9090).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
