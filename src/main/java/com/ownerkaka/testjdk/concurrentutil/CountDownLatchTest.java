package com.ownerkaka.testjdk.concurrentutil;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by akun on 2017/11/26.
 */
public class CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    ExecutorService pool = Executors.newFixedThreadPool(5);

    @Test
    public void test() throws InterruptedException {
        IntStream.range(1, 4).forEach(i -> {

            pool.submit(() -> {
                long count = countDownLatch.getCount();

                System.out.println("i:" + i + " before-count:" + count);
                countDownLatch.countDown();
                count = countDownLatch.getCount();
                System.out.println("i:" + i + " after-count:" + count);
            });
        });

        System.out.println("pool.isTerminated:" + pool.isTerminated());
        countDownLatch.await();
        System.out.println("main thread do.");
        System.out.println("pool.isTerminated:" + pool.isTerminated());
    }
}