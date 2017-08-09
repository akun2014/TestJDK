package com.gk.threadpool;

import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

/**
 * Created by akun on 2017/8/10.
 */
public class Sums {


    static class Sum implements Callable<Long> {
        private final long from;
        private final long to;

        Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            return acc;
        }
    }

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Long>> results = executor.invokeAll(asList(
                new Sum(0, 10), new Sum(100, 1_000), new Sum(10_000, 1_000_000)
        ));
//        executor.shutdown();

        System.out.println("isTerminated:" + executor.isTerminated());
        for (Future<Long> result : results) {
            System.out.println(result.get());
        }

        TimeUnit.SECONDS.sleep(5);
        Future<Long> future = executor.submit(new Sum(10, 100));
        System.out.println(future.get());
        System.out.println("isTerminated:" + executor.isTerminated());
        executor.shutdownNow();
        System.out.println("isTerminated:" + executor.isTerminated());
    }

}

