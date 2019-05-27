package com.ownerkaka.testjdk.guava.primitives;

import com.google.common.base.Charsets;
import com.google.common.primitives.*;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by akun on 2016/10/15.
 */

public class PrimitivesTest {


    @Test
    public void testInts() {


        int i = Ints.hashCode(1);
        UnsignedInteger unsignedInteger = UnsignedInteger.fromIntBits(1);
        int compare = UnsignedInts.compare(1, 1);

    }

    @Test
    public void testBytes() {

        byte minByte = -128;
        byte maxByte = 127;

        int hashCode = Bytes.hashCode(minByte);
    }

    @Test
    public void testShorts() {
        short s = 1;
        int hashCode = Shorts.hashCode(s);
    }

    @Test
    public void testFloats() {
        int hashCode = Floats.hashCode(1.1f);
    }

    @Test
    public void testDoubles() {
        int hashCode = Doubles.hashCode(1d);
    }

    @Test
    public void testChars() {
        char c1 = 'a';
        char c2 = '\u9999';
        char c3 = '中';
        char mixChar = 0;
        char maxChar = 65535;
        char charA = 98;
        char c = Chars.checkedCast(256);
        System.out.println(c);
    }

    @Test
    public void testBooleans() {
        int hashCode = Booleans.hashCode(false);
    }


    @Test
    public void testString() throws UnsupportedEncodingException {
        byte[] s_utf8 = new String("我").getBytes(Charsets.UTF_8.toString());
        byte[] s_UTF_16 = new String("我").getBytes(Charsets.UTF_16.toString());
        byte[] s_GBK = new String("我").getBytes("GBK");
        byte[] s_default = new String("我").getBytes();
        byte[] s_default_a = new String("a").getBytes();


        int bytes = Shorts.BYTES;
        int bytes1 = Chars.BYTES;
        int bytes2 = Ints.BYTES;
        int bytes3 = Longs.BYTES;
        int bytes4 = Floats.BYTES;
        int bytes5 = Doubles.BYTES;
        System.out.println(""+bytes+" "+bytes1+" "+bytes2+" "+bytes3+" "+bytes4+" "+bytes5);



        System.out.println(s_utf8.length + "s_UTF_16: "+s_UTF_16.length +"s_GBK: "+ s_GBK.length +" default:"+s_default.length);
        System.out.println(s_default_a.length);
    }


}

