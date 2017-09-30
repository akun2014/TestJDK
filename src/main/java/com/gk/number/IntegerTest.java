package com.gk.number;

import org.junit.Test;

/**
 * Created by akun on 2017/9/30.
 */
public class IntegerTest {

    /**
     * 数值类的包装类型，使用equals方法判断是否相等
     */
    @Test
    public void IntTest() {

        Integer a = 300;
        Integer c = 300;
        System.out.println(a.equals(c)); // true
        System.out.println(a == c);  // false
    }
}
