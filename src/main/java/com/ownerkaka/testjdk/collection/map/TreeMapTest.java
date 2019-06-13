package com.ownerkaka.testjdk.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by akun on 2018/5/31.
 */
@Slf4j
public class TreeMapTest {

    @Test
    public void test() {
//        Map<String, Integer> map = new TreeMap<>();//升序
        Map<String, Integer> map = new TreeMap<>(Collections.reverseOrder());//降序
        map.put("a", 1);
        map.put("b", 1);
        map.put("d", 1);
        map.put("c", 1);

        map.forEach((key, value) -> {
            log.info("key:{} value:{}", key, value);
        });
    }
}
