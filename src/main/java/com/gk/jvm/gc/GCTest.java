package com.gk.jvm.gc;

import org.junit.Test;

/**
 * Created by akun on 2018/4/10.
 */
public class GCTest {

    private static final int _1MB = 1024 * 1024;

    @Test
    public void allocationTest() {
        byte[] obj0 = new byte[2 * _1MB];
//        byte[] obj1 = new byte[2 * _1MB];
//        byte[] obj2 = new byte[2 * _1MB];
//        byte[] obj3 = new byte[4 * _1MB];
    }

    /**
     * 大对象直接进入老年代
     */
    @Test
    public void pretenureSieThresholdTest() throws Exception {
        byte[] obj0 = new byte[4 * _1MB];
    }

    @Test
    public void tenuringThresholdTest() throws Exception {
        byte[] obj0, obj1, obj2;
        obj0 = new byte[_1MB / 4];
        obj1 = new byte[4 * _1MB];
        obj2 = new byte[4 * _1MB];
        obj2 = null;
        obj2 = new byte[4 * _1MB];
    }
}