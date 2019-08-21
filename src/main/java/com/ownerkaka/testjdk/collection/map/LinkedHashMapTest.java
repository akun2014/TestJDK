package com.ownerkaka.testjdk.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by akun on 2018/5/31.
 */
@Slf4j
public class LinkedHashMapTest {

    @Test
    public void test() {
//        Map<String, Integer> map = new LinkedHashMap<>();插入顺序
        Map<String, Integer> map = new LinkedHashMap<>(16, 0.75F, true);//访问频次顺序，访问次数越多排在后面
        map.put("a", 1);
        map.put("b", 1);
        map.put("d", 1);
        map.put("c", 1);

        map.get("a");
        map.get("d");
        map.get("d");

        map.forEach((key, value) -> {
            log.info("key:{} value:{}", key, value);
        });
    }
}
