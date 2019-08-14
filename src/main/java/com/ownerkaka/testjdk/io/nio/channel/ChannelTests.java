package com.ownerkaka.testjdk.io.nio.channel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author akun
 * @since 2019-08-14
 * Java NIO中最重要的通道的实现：
 * <p>
 * FileChannel
 * <p>
 * DatagramChannel
 * <p>
 * SocketChannel
 * <p>
 * ServerSocketChannel
 * <p>
 * FileChannel 从文件中读写数据。
 * <p>
 * DatagramChannel 能通过UDP读写网络中的数据。
 * <p>
 * SocketChannel 能通过TCP读写网络中的数据。
 * <p>
 * ServerSocketChannel 可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 */
@Slf4j
public class ChannelTests {

    @Test
    public void test() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("application.properties", "rw");
        FileChannel channel = accessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);

        int read = channel.read(buffer);
        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        IOUtils.closeQuietly(accessFile);
    }
}