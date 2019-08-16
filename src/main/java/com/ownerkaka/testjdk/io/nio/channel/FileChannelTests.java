package com.ownerkaka.testjdk.io.nio.channel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import sun.nio.ch.FileChannelImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
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
public class FileChannelTests {

    @Test
    public void testFileChannel() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE);
        Assert.assertTrue(channel instanceof FileChannelImpl);

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
    public void testFileChannelWrite() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.APPEND);

        ByteBuffer data = StandardCharsets.UTF_8.encode("\ntest_fileChannel_write");
        channel.write(data);
        channel.force(true);
        IOUtils.closeQuietly(channel);
    }

    /**
     * 开辟直接内存做数据读写
     */
    @Test
    public void testFileChannelMap() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ);
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

        byte[] tmp = new byte[buffer.remaining()];
        buffer.get(tmp);

        log.info("{}", new String(tmp, StandardCharsets.UTF_8));
        IOUtils.closeQuietly(channel);
    }



}