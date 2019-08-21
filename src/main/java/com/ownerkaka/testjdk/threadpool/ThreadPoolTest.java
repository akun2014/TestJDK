package com.ownerkaka.testjdk.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by akun on 2017/7/28.
 */
public class ThreadPoolTest {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("named-pool-%d").build();

    /**
     * 任务队列使用无界阻塞队列
     * 拒绝策略采用discard
     */
    @Test
    public void threadPoolTest() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10, 10L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), namedThreadFactory, new ThreadPoolExecutor.DiscardPolicy());
        //预开启所有核心线程
        threadPool.prestartAllCoreThreads();
        //设置核心线程最长空闲时间
        threadPool.allowCoreThreadTimeOut(true);
        //尝试移除队列中被取消的Future tasks
        threadPool.purge();
        //预开启一个核心线程
//        threadPool.prestartCoreThread();
        threadPool.execute(() -> {
            System.out.println(RandomStringUtils.randomAlphabetic(2));
        });
    }

    /**
     * 任务队列使用有界阻塞队列（最多同时容纳20个任务）
     * 拒绝策略采用默认的Abort（拒绝任务时抛出RejectedExecutionException异常）
     */
    @Test
    public void threadPoolTest2() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 10L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), namedThreadFactory);
    }
}
