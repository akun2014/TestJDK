package com.ownerkaka.testjdk.io.nio.channel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
    public void testFileChannel() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE);

        channel = channel.position(5);//设定读取位置
        Assert.assertEquals(5, channel.position());

        ByteBuffer buffer = ByteBuffer.allocate(128);

        StringBuffer data = new StringBuffer();
        int read = channel.read(buffer);
        System.out.println(read);
        while (read != -1) {
            buffer.flip();
            String result = StandardCharsets.UTF_8.decode(buffer).toString();
            data.append(result);
            buffer.clear();
            read = channel.read(buffer);
        }
        log.info("{}", data.toString());
        IOUtils.closeQuietly(channel);
    }


    @Test
    public void testSocketChannel() throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("http://47.96.111.8", 80));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);

        while (read != -1) {
            buffer.flip();

            while (buffer.hasRemaining()) {
                String result = new String(buffer.array(), StandardCharsets.UTF_8);
                System.out.println(result);
            }

            buffer.clear();
            read = channel.read(buffer);
        }


        IOUtils.closeQuietly(channel);
    }
}