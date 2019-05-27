package com.ownerkaka.testjdk.threadpool;

import java.util.concurrent.*;

/**
 * Created by akun on 2017/11/23.
 */
public class FutureTaskTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("time:" + System.currentTimeMillis());
        };

        FutureTask futureTask = new FutureTask(runnable, Integer.class);
        ExecutorService pool = Executors.newSingleThreadExecutor();
        System.out.println(futureTask.isDone());
        pool.submit(futureTask);
//        futureTask.run();
        System.out.println(futureTask.isCancelled());
        try {
            futureTask.get(2,TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
