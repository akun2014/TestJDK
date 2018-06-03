package com.gk.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by akun on 2018/5/31.
 */
@Slf4j
public class HashSetTest {


    @Test
    public void test() {
        Set<String> set = new HashSet<>();
        set.add("aa");
        set.add("bb");
        set.add("cc");
        set.add("sdf");
        set.add("ces");
        set.add("ds");
        set.add("ds2");
        set.add("dsd");

        set.forEach(value -> {
            log.info("vaule:{}", value);
        });
    }
}
