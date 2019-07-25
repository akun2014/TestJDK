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
        LinkedHashMap map = new LinkedHashMap() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                Object key = eldest.getKey();
                Object value = eldest.getValue();
                boolean flag = super.size() > 3;
                return flag;
            }
        };
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);
        map.put("d", 1);

        map.get("a");
        map.get("d");
        map.get("d");

        map.forEach((key, value) -> {
            log.info("key:{} value:{}", key, value);
        });
    }

    /**
     * 元素被访问后，放到链表末尾
     */
    @Test
    public void testAccessOrder() {
        final int max_size = 3;
        Map<String, Integer> map = new LinkedHashMap(16, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                boolean flag = super.size() > max_size;
                return flag;
            }
        };

        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);
        map.put("d", 1);

        map.get("a");
        map.get("d");
        map.get("d");
        map.forEach((key, value) -> log.info("key:{} value:{}", key, value));
    }
}
