package com.gk.jvm.gc;

/**
 * Created by akun on 2018/4/10.
 */
public class GCTest {

    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) {
        allocationTest();

//        pretenureSieThresholdTest();

//        tenuringThresholdTest();
//        tenuringThreshold2Test();
//        handlerPromotionTest();
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -client
     * -Xmn 新生代大小
     */
    public static void allocationTest() {
        byte[] obj0 = new byte[2 * _1MB];
        byte[] obj1 = new byte[2 * _1MB];
        byte[] obj2 = new byte[2 * _1MB];
        byte[] obj3 = new byte[2 * _1MB];
        byte[] obj4 = new byte[2 * _1MB];
        byte[] obj5 = new byte[2 * _1MB];
        byte[] obj6 = new byte[4 * _1MB];
    }

    /**
     * 大对象直接进入老年代
     * -XX:PretenureSizeThreshold=3145728 -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -client
     */
    public static void pretenureSieThresholdTest() {
        byte[] obj0 = new byte[4 * _1MB];
    }

    /**
     * 长期存活对象进入老年代
     * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -client
     */
    public static void tenuringThresholdTest() {
        byte[] obj0, obj1, obj2;
        obj0 = new byte[_1MB / 4];
        obj1 = new byte[4 * _1MB];
        obj2 = new byte[4 * _1MB];
        obj2 = null;
        obj2 = new byte[4 * _1MB];
    }

    /**
     * -XX:MaxTenuringThreshold=5 -XX:+PrintTenuringDistribution -verbose:gc
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -client
     */
    public static void tenuringThreshold2Test() {
        byte[] obj0, obj1, obj2;
        obj0 = new byte[_1MB / 4];
        obj1 = new byte[4 * _1MB];
        obj2 = new byte[4 * _1MB];
        obj2 = null;
        obj2 = new byte[4 * _1MB];
    }

    /**
     * -XX:-HandlePromotionFailure
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8 -client
     */
    public static void handlerPromotionTest() {
        byte[] obj0, obj1, obj2, obj3, obj4, obj5, obj6;
        obj0 = new byte[2 * _1MB];
        obj1 = new byte[2 * _1MB];
        obj2 = new byte[2 * _1MB];
        obj0 = null;
        obj3 = new byte[2 * _1MB];
        obj4 = new byte[2 * _1MB];
        obj5 = new byte[2 * _1MB];
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = new byte[2 * _1MB];
    }
}