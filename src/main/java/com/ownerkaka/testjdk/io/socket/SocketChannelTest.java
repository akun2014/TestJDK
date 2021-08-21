package com.ownerkaka.testjdk.io.socket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author akun
 * @Date 2021/8/21 11:57
 */
@Slf4j
public class SocketChannelTest {

    @Test
    @SneakyThrows
    public void clientTest() {
    }

    @Test
    @SneakyThrows
    public void serverTest() {
        //创建选择器
        Selector selector = Selector.open();
        log.info("selector isOpen={}", selector.isOpen());

        //打开一个通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(9090);
        ssc.bind(address);
        log.info("serverSocketChannel isOpen={}", ssc.isOpen());
        log.info("serverSocketChannel isRegistered={}", ssc.isRegistered());
        log.info("serverSocketChannel isBlocking={}", ssc.isBlocking());

        //通道注册到选择器上
        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        log.info("serverSocketChannel isRegistered={}", ssc.isRegistered());
        log.info("serverSocketChannel isBlocking={}", ssc.isBlocking());
        while (true) {
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey key;

            while (iterator.hasNext()) {
                key = iterator.next();
                iterator.remove();

                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel accept = channel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer_r = ByteBuffer.allocate(1024);
                        int read = sc.read(buffer_r);
                        if (read > 0) {
                            buffer_r.flip();
                            byte[] b = new byte[buffer_r.remaining()];
                            buffer_r.get(b);
                            String s = new String(b, Charset.defaultCharset());
                            log.info("from client s={}", s);
                        } else if (read < 0) {
                            key.channel();
                            sc.close();
                        }
                        ByteBuffer buffer_w = ByteBuffer.allocate(1024);
                        buffer_w.put("hello".getBytes(StandardCharsets.UTF_8));
                        buffer_w.flip();
                        sc.write(buffer_w);
                    } else if (key.isWritable()) {

                    } else if (key.isConnectable()) {
                    }
                }
            }
        }
    }

}
