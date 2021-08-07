package com.ownerkaka.testjdk.lock;

import com.ownerkaka.testjdk.zookeeper.ZkClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.*;

/**
 * zk实现分布式锁
 */

@Slf4j
public class CuratorLockTest {
    private static final int THREAD_COUNT = 50;
    private static int count = 0;

    static final ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
    static final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
    static final CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT);

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < THREAD_COUNT; i++) {
            pool.execute(() -> {
                CuratorFramework client = ZkClientUtil.getClient();
                InterProcessMutex lock = new InterProcessMutex(client, "/mylock");
                try {
                    log.info("NumberWaiting={}", cyclicBarrier.getNumberWaiting());
                    cyclicBarrier.await();
                    if (lock.acquire(3000, TimeUnit.MILLISECONDS)) {
                        log.info("locked by thread id={}", Thread.currentThread().getId());
                        count++;
                    }
                } catch (Exception ignore) {
                } finally {
                    countDownLatch.countDown();
                    try {
                        lock.release();
                    } catch (Exception ignore) {
                    }
                }

            });
        }
        countDownLatch.await();

        log.info("count=50？{},{}", count == 50, count);
    }


}
