package com.ownerkaka.testnetty.netty;

import com.ownerkaka.testnetty.netty.handler.SubscribeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author akun
 * @Date 2021/8/21 20:23
 */
public class NettyClientTest {


    NioEventLoopGroup workerGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));

    @Test
    public void clientStart() {
        try {
            Bootstrap boot = new Bootstrap().group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(
                                            new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader()))
                                    )
                                    .addLast(new ObjectEncoder())
                                    .addLast(new IdleStateHandler(0, 0, 100, TimeUnit.MILLISECONDS))
                                    .addLast(new SubscribeClientHandler());
                        }
                    });
            ChannelFuture channelFuture = boot.connect("127.0.0.1", 9090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }


    }
}
