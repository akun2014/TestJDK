package com.ownerkaka.testjdk.io.nio.buffer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.nio.*;

/**
 * @Author akun
 * @Date 2021/8/22 16:35
 */
@Slf4j
public class BufferTest {

    @Test
    public void testBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        ByteBuffer.allocateDirect(100);
        buffer.putInt(100);
        Assert.assertEquals(4, buffer.position());
        buffer.putChar('C');
        Assert.assertEquals(4 + 2, buffer.position());
        buffer.putShort((short) 12);
        Assert.assertEquals(4 + 2 + 2, buffer.position());
        buffer.putLong(100L);
        Assert.assertEquals(4 + 2 + 2 + 8, buffer.position());
        buffer.putFloat(12.0F);
        Assert.assertEquals(4 + 2 + 2 + 8 + 4, buffer.position());
        buffer.putDouble(20.0D);
        Assert.assertEquals(4 + 2 + 2 + 8 + 4 + 8, buffer.position());
        buffer.putChar('码');
        Assert.assertEquals(4 + 2 + 2 + 8 + 4 + 8 + 2, buffer.position());
    }

    @Test
    public void bufferType() {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(10);
        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.put('A');
        ShortBuffer shortBuffer = ShortBuffer.allocate(10);
        shortBuffer.put((short) 10);
        LongBuffer longBuffer = ByteBuffer.allocate(10).asLongBuffer();
        longBuffer.put(10L);
        FloatBuffer floatBuffer = ByteBuffer.allocate(10).asFloatBuffer();
        floatBuffer.put(10F);
        DoubleBuffer doubleBuffer = ByteBuffer.allocate(10).asDoubleBuffer();
        doubleBuffer.put(10D);
    }

    @Test
    public void mappedByteBufferTest() {
        //heap上分配内存
        ByteBuffer buffer = MappedByteBuffer.allocate(10);
        //分配直接内存
        ByteBuffer buffer1 = MappedByteBuffer.allocateDirect(10);
    }
}
