package com.gk.chartype;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by akun on 2018/7/18.
 * 在Java中 基本数据类型 char 在内存中占2个byte。
 * 在字符串getBytes时，如果没有显示传Charset，将会使用平台的默认字符集（mac一般为utf-8）
 * getBytes得到的字节数组长度，由编码方式决定。
 * e.g. utf-8 下中文 3个byte； gbk下，中文2个byte
 */
@Slf4j
public class CharTest {


    @Test
    public void test() {
        char c1 = '山';
        String c2 = "山";
        char c3 = 23665;
        char c4 = '\u6211';

        byte[] bytes = c2.getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes.length); // print 3
        byte[] gbk = c2.getBytes(Charset.forName("gbk"));
        System.out.println(gbk.length); // print 2

        toHex(bytes);

    }

    @Test
    public void test2() throws Exception {
        byte[] bytes_utf8 = new byte[]{-27, -79, -79};
        String s = new String(bytes_utf8, StandardCharsets.UTF_8);// this is default charset
        System.out.println(s);// print 山

        byte[] bytes_gbk = new byte[]{-55, -67};
        String str = new String(bytes_gbk, Charset.forName("gbk"));
        System.out.println(str); // print 三
    }

    private void toHex(byte[] bytes) {
        for (byte b : bytes) {
            String toHexString = Integer.toHexString((int) b);
            log.info("{} -", toHexString);
        }
    }


}
