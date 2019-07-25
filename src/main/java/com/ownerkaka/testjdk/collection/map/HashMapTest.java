package com.ownerkaka.testjdk.collection.map;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akun on 2018/5/16.
 */
public class HashMapTest {

    @Test
    public void test() throws Exception {
        Map<String, String> map = new HashMap<>(8, 0.75f);
//        Map<Integer, String> map = new HashMap<>();

        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 64000000; i++) {
            map.put(RandomStringUtils.randomAlphabetic(5), "abc");
        }
        stopwatch.elapsed();
        System.out.println("time:" + stopwatch);
        Class<?> mapType = map.getClass();
        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(map));

        Field size = mapType.getDeclaredField("size");
        size.setAccessible(true);
        System.out.println("size : " + size.get(map));

        Field threshold = mapType.getDeclaredField("threshold");
        threshold.setAccessible(true);
        System.out.println("threshold : " + threshold.get(map));

        Field loadFactor = mapType.getDeclaredField("loadFactor");
        loadFactor.setAccessible(true);
        System.out.println("loadFactor : " + loadFactor.get(map));

    }
}
