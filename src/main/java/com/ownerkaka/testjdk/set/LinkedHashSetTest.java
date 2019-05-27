package com.ownerkaka.testjdk.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by akun on 2018/5/31.
 */
@Slf4j
public class LinkedHashSetTest {
    @Test
    public void test() {
        Set<String> set = new LinkedHashSet<>();
        set.add("bb");
        set.add("aa");
        set.add("cc");
        set.add("sdf");
        set.add("ces");
        set.add("ds");

        set.contains("bb");
        set.contains("cc");
        set.contains("cc");

        //以元素的添加顺序访问集合的元素
        set.forEach(value -> {
            log.info("vaule:{}", value);
        });
    }
}
