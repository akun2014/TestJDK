package com.gk.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by akun on 2017/7/28.
 */
public class ThreadPoolTest {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("named-pool-%d").build();

    @Test
    public void threadPoolTest() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), namedThreadFactory);
        ExecutorService cachedThreadPool = (ExecutorService) threadPoolExecutor;
    }
}
