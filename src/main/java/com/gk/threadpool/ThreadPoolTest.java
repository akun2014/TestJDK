package com.gk.threadpool;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by akun on 2017/7/28.
 */
public class ThreadPoolTest {


    @Test
    public void threadPoolTest() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }
}
