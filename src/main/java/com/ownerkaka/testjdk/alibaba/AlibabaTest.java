package com.ownerkaka.testjdk.alibaba;

/**
 * Created by akun on 2017/7/5.
 */
public class AlibabaTest {

    static SimpleCache simpleCache = new SimpleCache();

    public static void main(String[] args) {
        simpleCache.cache("key1", "123");
        Object key = simpleCache.get("key1");
        Object key2 = simpleCache.get("key2");
        System.out.println(key);
        System.out.println(key2);
    }
}
