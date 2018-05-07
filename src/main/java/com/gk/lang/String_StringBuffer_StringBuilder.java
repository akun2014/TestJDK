package com.gk.lang;

import org.junit.Test;

/**
 * @author gk
 *         <p>
 *         StringBuffer是线程安全的，在方法上都加了synchronized关键字
 *         StringBuilder是StringBuffer 的一个简易替代，是非线程安全的
 */
public class String_StringBuffer_StringBuilder {

    @Test
    public void stringTest() {
        String str = "1";
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            str += "1";
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }

    @Test
    public void stringbufferTest() {
        StringBuffer sb = new StringBuffer("0");
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            sb.append(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }

    @Test
    public void stringbuilderTest() {
        StringBuilder sbder = new StringBuilder();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            sbder.append(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }
}
