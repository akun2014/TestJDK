package com.ownerkaka.testjdk.chartype;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
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

    @Test
    public void test4() {
        int i = 18890 + 65536;
//大于65535即mod，此处加了好几个65535，其还是代表18890
//int i = 18890 + 65536 + 65536 + 65536;
//int i = 18890 - 65536 - 65536;
        char c = (char) i;
        System.out.println(c);
//mod针对大于65535的还好使，但是其代表的是这个意思
        System.out.println(i % 65536);
//Integer.valueOf()能得到其代表的unicode值的大小，十进制int表示
        System.out.println(Integer.valueOf(c));
        char d = '䧊';
        int n = (int) d;
        System.out.println(n);
    }

    public static void main(String[] args) {
        String str = "中";
        char x = '中';
        byte[] bytes = null;
        byte[] bytes1 = null;
        byte[] bytes2 = null;
        try {
            bytes = str.getBytes("gbk");
            bytes1 = str.getBytes("utf-8");
            bytes2 = charToByte(x);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("bytes 大小：" + bytes.length);
        System.out.println("bytes1大小：" + bytes1.length);
        System.out.println("char转换为byte数组大小：" + bytes2.length);
        System.out.println("byte数组的两个值，其实unicode值的两个字节拆分到数组里了：" + bytes2[0] + "-" + bytes2[1]);
    }

    // 如"中"的unicode值为Integer.valueOf(x)=20013
    // 二进制为0100 1110 0010 1101
    // 拆分出来即为b[0]=0100 1110 b[1]=0010 1101
    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }


}
