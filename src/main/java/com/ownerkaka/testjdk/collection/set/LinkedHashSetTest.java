package com.ownerkaka.testjdk.collection.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by akun on 2018/5/31.
 * 基于LinkedHashMap实现
 */
@Slf4j
public class LinkedHashSetTest {
    @Test
    public void test() {
        Set<String> set = new LinkedHashSet<>();
        SetUtil.addElement(set);

        Assert.assertArrayEquals(new String[]{"aa", "bb", "ca", "cb", "daa", "da"}, set.toArray(new String[0]));
    }
}
