package com.gk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<String> list = new ArrayList<>();
        String[] strings = list.toArray(new String[Collections.emptyList().size()]);
        Assert.notNull(strings);
    }


}