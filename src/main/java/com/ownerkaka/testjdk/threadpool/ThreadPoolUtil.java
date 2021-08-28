package com.ownerkaka.testjdk.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author akun
 * @since 2019-07-31
 */
@Slf4j
public class ThreadPoolUtil {

    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 100,
            0, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("named-pool-%d").build(),
            new ThreadPoolExecutor.DiscardPolicy());

    static {
        threadPool.prestartAllCoreThreads();
    }

    public static ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    public static ThreadPoolExecutor getThreadPool(int size) {
        return new ThreadPoolExecutor(size, size,
                0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(500),
                new ThreadFactoryBuilder().setNameFormat("named-pool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}