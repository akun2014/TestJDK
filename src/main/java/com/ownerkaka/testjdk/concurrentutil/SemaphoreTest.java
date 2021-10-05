package com.ownerkaka.testjdk.concurrentutil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author akun
 * @Date 2021/10/5 13:32
 * 控制并发数
 */
@Slf4j
public class SemaphoreTest {

    private static final int MAX_THREAD = 20;
    private final Semaphore semaphore = new Semaphore(1);
    private final Executor executor = Executors.newFixedThreadPool(MAX_THREAD);
    int num = 0;
    private final CountDownLatch latch = new CountDownLatch(MAX_THREAD);

    @SneakyThrows
    @Test
    public void test() {
        for (int i = 0; i < MAX_THREAD; i++) {
            executor.execute(() -> {
                boolean acquire = semaphore.tryAcquire();
                if (acquire) {
                    num++;
                }
                semaphore.release();
                latch.countDown();
            });
        }
        latch.await();

        Assert.assertEquals(MAX_THREAD, num);
    }
}
