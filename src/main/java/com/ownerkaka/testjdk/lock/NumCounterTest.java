package com.ownerkaka.testjdk.lock;

import com.ownerkaka.testjdk.threadpool.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author akun
 * @Date 2021/8/28 21:12
 * 使用多线程执行并发累加操作
 */
@Slf4j
public class NumCounterTest {

    final static int thread_num = 50;
    final static int loop_num = 1000;
    final static ThreadPoolExecutor pool = ThreadPoolUtil.getThreadPool(thread_num);
    final static CountDownLatch latch = new CountDownLatch(thread_num);
    final static CyclicBarrier cyclicBarrier = new CyclicBarrier(thread_num);

    static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < thread_num; i++) {
            pool.execute(() -> {
                long await = 0, counter, counterFlag = 0;
                try {
                    counterFlag = latch.getCount();
                    //模拟多线程并发
                    await = cyclicBarrier.await();
                    for (int j = 0; j < 1000; j++) {
                        //使用同步锁，保证线程安全
                        synchronized (NumCounterTest.class) {
                            num++;
                        }
                    }
                } catch (Exception e) {
                    log.error("", e);
                } finally {
                    counter = latch.getCount();
                    //确保每个线程都执行完
                    latch.countDown();
                }
                log.info("counterFlag ={}, await num={} counter={}", counterFlag, await, counter);
            });
        }
        latch.await();
        cyclicBarrier.reset();
        pool.shutdown();

        Assert.assertEquals("must be equals!", thread_num * loop_num, num);

        log.info("awaitTermination={}", pool.awaitTermination(5, TimeUnit.SECONDS));
        log.info("isShutdown={},isTerminating={},isTerminated={}", pool.isShutdown(), pool.isTerminating(), pool.isTerminated());

    }
}
