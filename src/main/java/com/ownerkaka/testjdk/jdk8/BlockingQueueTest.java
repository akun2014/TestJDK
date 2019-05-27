package com.ownerkaka.testjdk.jdk8;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by akun on 2017/7/11.
 */
public class BlockingQueueTest {

    BlockingDeque<Runnable> deque = new LinkedBlockingDeque<>();
    BlockingQueue<Runnable> queue = new LinkedBlockingQueue();
    BlockingQueue<Runnable> arrQueue = new ArrayBlockingQueue(5);
    Runnable runnable;

    @Before
    public void init() {
         runnable = () ->{
            String name = Thread.currentThread().getName() + RandomStringUtils.randomAlphabetic(2).toUpperCase();
             System.out.println(name + "-running. " + getTimestamp());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             System.out.println(name + "-finished. " + getTimestamp());
        };
    }

    @Test
    public void ArrayBlockingQueueTest() {
        for (int i = 0; i < 5; i++) {
            arrQueue.offer(runnable);
        }

        while (!arrQueue.isEmpty()) {
            arrQueue.poll().run();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void LinkedBlockingQueue() {

        Runnable runnable1 = ()->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("runnable1 offering" + getTimestamp());
                queue.offer(runnable);
                queue.add(runnable);
                try {
                    queue.put(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("runnable1 offered" + getTimestamp());
            }
        };
        runnable1.run();

        Runnable runnable2 = ()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("runnable2 offering" + getTimestamp());
                queue.offer(runnable);
                System.out.println("runnable2 offered" + getTimestamp());
            }
        };
        runnable2.run();



        while (!queue.isEmpty()) {
            queue.poll().run();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTimestamp() {
        return " "+new DateTime().toString("mm:ss.S");
    }

}
