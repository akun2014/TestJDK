package com.gk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by akun on 2018/5/10.
 */
@Slf4j
public class CommonTest {
    String name = "gk";

    public static int test(int n) {
        return n;
    }

    public void test() {
    }

    private void privateMethod() {

    }

    protected void protectedMethod() {

    }

    public final void finalMethod() {

    }

    public static void main(String[] args) {

        CommonTest commonTest = new CommonTest();
        commonTest.test();
        commonTest.privateMethod();
        commonTest.protectedMethod();
        commonTest.finalMethod();
        CommonTest.test(1);
    }

    @Test
    public void testt() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String name = contextClassLoader.getClass().getName();
        System.out.println(name);
    }


}