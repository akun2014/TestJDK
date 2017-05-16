package com.gk.commons.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by akun on 16/4/6.
 */
public class StringUtilsTest {


    /**
     * join 对基础类型的数组进行字符串拼接
     * startIndex endIndex 用于选取需要拼接的数组区间
     */
    @Test
    public void testJoin() {

        List<String> strings = Arrays.asList("gui", "kun", "aa");
        String join = StringUtils.join(strings, ",");

        System.out.println(join);
    }

    /**
     * substring  用于字符串的截取
     */
    @Test
    public void testSubstring() {
        String str = "asfhsfjlas;df";
        String substring = StringUtils.substring(str, 1);

        System.out.println(substring);
    }

    /**
     * contains 查看给定的字符串是否包含查询的字符序列
     */
    @Test
    public void testContains() {
        String string = "guiku ";
        boolean contains = StringUtils.contains(string, "gui");

        assertTrue(contains);
    }

    @Test
    public void tripTest() {
        String str = " adf";
        String stripStr = "a";
        String strip = StringUtils.strip(str, stripStr);
        System.out.println("result:"+strip);
    }

    public void test3() {

    }
}
