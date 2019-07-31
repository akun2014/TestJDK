package com.ownerkaka.testjdk.lang;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author akun
 * @since 2019-07-31
 * This class provides thread-local variables。
 * ThreadLocal提供线程本地变量，保证该变量只在当前线程可见
 */
@Slf4j
public class ThreadLocalTest {

    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 100, 10L,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("pool-%d").build());
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private Random random = new Random();


    @BeforeClass
    public static void init() {
        threadPool.prestartAllCoreThreads();
    }


    /**
     * ThreadLocal保证当前线程保存的数据不被其他线程修改，即统一线程set(Obj)设置的数据，与get()相同
     */
    @Test
    public void test() {
        //创建一个task，在同一个线程下对ThreadLocal进行先写后读操作，测试读到的数据是否和写入的数据一致
        Runnable task = () -> {
            threadLocal.remove();
            String str = RandomStringUtils.randomAlphabetic(4);
            threadLocal.set(str);
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
            } catch (InterruptedException ignore) {
            }
            String oldStr = threadLocal.get();
            Assert.assertEquals(str, oldStr);
        };

        //创建90个线程做相同的读写操作，模拟多线程环境
        for (int i = 0; i < 90; i++) {
            threadPool.submit(task);
        }


        while (threadPool.getActiveCount() > 0) {
        }
    }
}