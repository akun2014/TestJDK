package com.ownerkaka.testjdk.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by akun on 2018/5/31.
 * <p>
 * 复写removeEldestEntry方法实现LRU
 */
@Slf4j
public class LinkedHashMapTest {


    /**
     * FIFO
     */
    @Test
    public void test() {
        final int max_size = 5;
        LinkedHashMap map = new LinkedHashMap() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return super.size() > max_size;
            }
        };
        MapUtil.putElement(map);
        map.get("aa");
        map.get("bb");
        Set<String> keySet = map.keySet();
        //element "aa" will be remove from map
        Assert.assertArrayEquals(new String[]{"bb", "ca", "cb", "daa", "da"}, keySet.toArray(new String[0]));
    }

    /**
     * LRU
     */
    @Test
    public void testAccessOrder() {
        final int max_size = 64;
        Map<String, Integer> map = new LinkedHashMap(16, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                boolean flag = super.size() > max_size;
                return flag;
            }
        };

        MapUtil.putElement(map);

        map.get("aa");
        map.get("bb");
        Set<String> keySet = map.keySet();
        Assert.assertArrayEquals(new String[]{"ca", "cb", "daa", "da", "aa", "bb"}, keySet.toArray(new String[0]));
    }
}
