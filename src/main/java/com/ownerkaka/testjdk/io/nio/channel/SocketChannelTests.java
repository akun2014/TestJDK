package com.ownerkaka.testjdk.io.nio.channel;

import com.ownerkaka.testjdk.common.entity.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author akun
 * @since 2019-08-16
 */
@Slf4j
public class SocketChannelTests {

    @Test
    public void clientChannel() throws IOException, InterruptedException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("localhost", Constants.port));
        log.info("channel.isConnected={}", channel.isConnected());
        if (!channel.isConnected()) {
            while (!channel.finishConnect()) {
                log.info("连接服务器。。。");
            }
        }
        log.info("channel.isConnected={}", channel.isConnected());

        String data = "hello server";
        ByteBuffer buffer = ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
        TimeUnit.HOURS.sleep(1);
    }
}