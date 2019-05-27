package com.ownerkaka.testjdk.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by akun on 2018/5/31.
 */
@Slf4j
public class TreeSetTest {
    @Test
    public void test() {
//        Set<String> set = new TreeSet<>();//自然顺序
        Set<String> set = new TreeSet<>(Comparator.reverseOrder());//降序
        set.add("aa");
        set.add("bb");
        set.add("cc");
        set.add("sdf");
        set.add("ces");
        set.add("ds");

        set.contains("bb");
        set.contains("cc");
        set.contains("cc");

        set.forEach(value -> {
            log.info("vaule:{}", value);
        });
    }
}
