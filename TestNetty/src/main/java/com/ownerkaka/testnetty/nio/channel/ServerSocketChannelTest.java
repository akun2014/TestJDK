package com.ownerkaka.testnetty.nio.channel;

import com.ownerkaka.testcommon.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author akun
 * @since 2019-08-16
 * Basic Reactor Design
 */
@Slf4j
public class ServerSocketChannelTest {


    @Test
    public void clientChannel() throws IOException, InterruptedException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("localhost", Constants.PORT));
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

    @Test
    public void serverSocket() throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(Constants.PORT));
        log.info("serverSocketChannel isOpen={}", serverSocketChannel.isOpen());
        log.info("serverSocketChannel isRegistered={}", serverSocketChannel.isRegistered());
        log.info("serverSocketChannel isBlocking={}", serverSocketChannel.isBlocking());

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("serverSocketChannel isRegistered={}", serverSocketChannel.isRegistered());
        while (!Thread.interrupted()) {
            //每 3 秒阻塞等待就绪的 IO 事件
            int selected = selector.select(3 * 1000);
            if (selected == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            log.info("selector.keys size={}", selector.keys().size());
            log.info("selectionKeys size={}", selectionKeys.size());
            log.info("--------loop-----------");
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (!key.isValid()) {
                    continue;
                }
                log.info("key.interestOps={}", key.interestOps());
                if (key.isAcceptable()) {
                    handleAcceptable(key, selector);
                } else if (key.isReadable()) {
                    handleReadable(key);
                } else if (key.isWritable()) {
                    handleWritable(key);
                }

            }
            selectionKeys.clear();
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

//        channel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    private void handleReadable(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        log.info("handled socketChannel.hashCode={}", channel.hashCode());
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        if (read != -1) {
            String msg = new String(buffer.array()).trim();
            log.info("server receive msg from client：{}", msg);
        }
//        channel.register(selector, SelectionKey.OP_READ, key.attachment());
    }

    private void handleAcceptable(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = server.accept();
        socketChannel.configureBlocking(false);

        socketChannel.write(ByteBuffer.wrap("Hello Client! i'm Server".getBytes()));

        socketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<String>());
        log.info("socketChannel.hashCode={}", socketChannel.hashCode());
    }
}