package com.ownerkaka.testjdk.io.nio.channel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author akun
 * @since 2019-08-16
 */
@Slf4j
public class ServerSocketChannelTests {
    private static Selector selector;

    @Test
    public void testServerSocketChannel() throws IOException {
        selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9090));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //每 30 秒阻塞等待就绪的 IO 事件
            int selected = selector.select(30 * 1000);
            if (selected == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    handleAcceptable(key);
                }
                if (key.isReadable()) {
                    handleReadable(key);
                }
                if (key.isWritable()) {
                    handleWritable(key);
                }
                iterator.remove();
            }
        }
    }

    private void handleWritable(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        List<String> responseQueue = (List<String>) key.attachment();

        for (String content : responseQueue) {
            content = content + "\n";
            channel.write(ByteBuffer.wrap(content.getBytes()));
        }
        responseQueue.clear();

        channel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    private void handleReadable(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer buffer = CodecUtil.read(channel);
        if (buffer != null) {
            if (buffer.position() > 0) {
                String msg = new String(buffer.array()).trim();
                log.info("server receive msg from client：{}", msg);

                List<String> responseQueue = (List<String>) key.attachment();
                responseQueue.add("server响应：" + msg);
                channel.register(selector, SelectionKey.OP_WRITE, key.attachment());
            }
        } else {
            channel.register(selector, 0);
        }
    }

    private void handleAcceptable(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = server.accept();
        socketChannel.configureBlocking(false);

        socketChannel.write(ByteBuffer.wrap("Hello Client! i'm Server".getBytes()));

        socketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<String>());
    }
}