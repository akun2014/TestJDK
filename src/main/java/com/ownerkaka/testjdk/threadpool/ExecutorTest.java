package com.ownerkaka.testjdk.threadpool;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


/**
 * Created by akun on 2017/7/9.
 */
public class ExecutorTest {

    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

    Runnable runnable;
    Callable<String> callable;

    @Before
    public void init() {
        runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            System.out.println("randomString:" + RandomStringUtils.randomAlphabetic(5));
        };

        callable = () -> {
            try {
                System.out.println("callable sleeping...");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("callable sleep finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            String randomStr = RandomStringUtils.randomAlphabetic(5);
            return randomStr;
        };
    }


    @Test
    public void cachedThreadPoolTest() throws ExecutionException, InterruptedException {
//        cachedThreadPool.execute(runnable);

        Future<String> future = cachedThreadPool.submit(callable);
        try {
            String s = future.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        while (!future.isDone()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("waiting...");
        }
        String s = future.get();
        System.out.println("return:" + s);
    }

    @Test
    public void singleThreadPoolTest() throws InterruptedException, TimeoutException, ExecutionException {
    }

    @Test
    public void fixedThreadPoolTest() throws InterruptedException {

        fixedThreadPool.awaitTermination(1, TimeUnit.MILLISECONDS);
    }

    @Test
    public void schedulePollTest() throws InterruptedException {
        Runnable run = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        scheduledThreadPool.scheduleAtFixedRate(run, 0, 2, TimeUnit.SECONDS);
//        scheduledExecutorService.scheduleWithFixedDelay(run, 0, 1, TimeUnit.SECONDS);
        TimeUnit.MINUTES.sleep(2);
    }

    @Test
    public void reduceTest() {
        Integer total2 = Stream.of(1, 2, 3, 4, 5).reduce(0, (x, y) -> x + y);
        assertEquals(new Integer(15), total2);
    }
}
