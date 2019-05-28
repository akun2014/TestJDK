package com.ownerkaka.testjdk.guava;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author akun
 * @since 2019-05-28
 */
@Slf4j
public class RateLimiterTest {
    private final RateLimiter limiter = RateLimiter.create(5);
    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("rateLimiter-pool-%d").build();

    private ExecutorService threadPool = new ThreadPoolExecutor(4, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

//    ExecutorService threadPool = Executors.newFixedThreadPool(2);


    @Test
    public void test() throws Exception {
        int masTaskNum = 50;
        for (int i = 0; i < masTaskNum; i++) {
            Runnable task = this::rateLimit;
            threadPool.submit(task);
        }

        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    private void rateLimit() {
        long threadId = Thread.currentThread().getId();
        limiter.acquire();
        log.info("try acquire done threadId:{}", threadId);
    }
}