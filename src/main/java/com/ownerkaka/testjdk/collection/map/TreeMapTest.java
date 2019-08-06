package com.ownerkaka.testjdk.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;

/**
 * Created by akun on 2018/5/31.
 * A Red-Black tree based NavigableMap implementation.
 * 基于红黑树实现的可导航map实现
 */
@Slf4j
public class TreeMapTest {

    /**
     * reverse of the natural ordering
     */
    @Test
    public void testReverseOrder() {
        Map<String, Integer> map = new TreeMap<>(Collections.reverseOrder());
        MapUtil.putElement(map);

        NavigableSet<String> keySet = ((TreeMap<String, Integer>) map).navigableKeySet();
        Assert.assertArrayEquals(new String[]{"daa", "da", "cb", "ca", "bb", "aa"}, keySet.toArray(new String[0]));
    }

    /**
     * natural ordering
     */
    @Test
    public void test() {
        Map<String, Integer> map = new TreeMap<>();
        MapUtil.putElement(map);

        NavigableSet<String> keySet = ((TreeMap<String, Integer>) map).navigableKeySet();
        Assert.assertArrayEquals(new String[]{"aa", "bb", "ca", "cb", "da", "daa"}, keySet.toArray(new String[0]));
    }
}
