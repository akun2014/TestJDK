package com.ownerkaka.testjdk.lock;

import com.ownerkaka.testjdk.zookeeper.ZkClientUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class zkLockTest {

    final ExecutorService pool = Executors.newFixedThreadPool(200);
    private CuratorFramework client;

    @Before
    public void init() {
        client = ZkClientUtil.client;
    }


    @Test
    public void test() throws Exception {
        long id = Thread.currentThread().getId();
        int threadSize = 1000;

        CountDownLatch latch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            pool.execute(new Mylocal(client, id, latch));
            latch.countDown();
        }
        latch.await();
        TimeUnit.SECONDS.sleep(3);
    }

    @AllArgsConstructor
    public class Mylocal implements Runnable {
        private CuratorFramework client;
        private Long threadId;
        private CountDownLatch latch;

        @SneakyThrows
        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            try {
                String result = client.create()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath("/" + threadId);
                System.out.printf("locked=" + result + " id=" + id);
            } catch (Exception e) {
                System.out.println("unlocked");
            } finally {
            }
        }
    }


}
