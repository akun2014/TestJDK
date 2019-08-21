package com.ownerkaka.testjdk.collection.map;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by akun on 2019/8/6.
 */
@Slf4j
public class MapUtil {

    public static void putElement(Map<String, Integer> map) {
        map.put("aa", 1);
        map.put("bb", 1);
        map.put("ca", 1);
        map.put("cb", 1);
        map.put("daa", 1);
        map.put("da", 1);
    }
}
