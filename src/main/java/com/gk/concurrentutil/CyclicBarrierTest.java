package com.gk.concurrentutil;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by akun on 2017/11/26.
 */
public class CyclicBarrierTest {
    ExecutorService pool = Executors.newFixedThreadPool(15);
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);


    @Test
    public void test() {

        IntStream.rangeClosed(1,12).forEach(i ->{

            System.out.println("i:" + i);
            pool.submit(()->{
                try {
                    long timeout;
                    if (i % 2 == 0) {
                        timeout = 3;
                    } else {
                        timeout = 5;
                    }
                    TimeUnit.SECONDS.sleep(timeout);
                    cyclicBarrier.wait();
                    System.out.println("wait finished i:" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        });
        System.out.println("getNumberWaiting:" + cyclicBarrier.getNumberWaiting());

    }
}
