package com.gk.encoding;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author akun
 */
@Slf4j
public class EncodingTest {

    @Test
    public void testEncoding() throws UnsupportedEncodingException {
        String str = "我wo";

        Assert.assertEquals(5, str.getBytes(StandardCharsets.UTF_8).length);
        Assert.assertEquals(3, str.getBytes(StandardCharsets.US_ASCII).length);
        Assert.assertEquals(4, str.getBytes("GBK").length);
        Assert.assertEquals(3, str.getBytes("iso-8859-1").length);
        Assert.assertEquals(8, str.getBytes(StandardCharsets.UTF_16).length);
    }
}
