package com.ownerkaka.testjdk.collection.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by akun on 2018/5/31.
 * 基于TreeMap实现
 *
 * @see com.ownerkaka.testjdk.collection.map.TreeMapTest
 */
@Slf4j
public class TreeSetTest {

    /**
     * 降序
     */
    @Test
    public void testReverseOrder() {
        Set<String> set = new TreeSet<>(Comparator.reverseOrder());
        SetUtil.addElement(set);

        Assert.assertArrayEquals(new String[]{"daa", "da", "cb", "ca", "bb", "aa"}, set.toArray(new String[0]));
    }

    /**
     * 自然排序
     */
    @Test
    public void test() {
        Set<String> set = new TreeSet<>();
        SetUtil.addElement(set);

        Assert.assertArrayEquals(new String[]{"aa", "bb", "ca", "cb", "da", "daa"}, set.toArray(new String[0]));
    }
}
