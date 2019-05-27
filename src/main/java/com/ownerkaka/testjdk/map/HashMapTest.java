package com.ownerkaka.testjdk.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akun on 2018/5/16.
 */
public class HashMapTest {

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>(4);
        map.put("abc", "abc");
        map.put("abcde", "abcde");
        map.put("ab", "ab");
        map.put("ab1", "ab");

        String value = map.get("abc");
    }
}
