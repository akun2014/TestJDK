package com.gk.forkJoinFrame;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by akun on 2017/8/9.
 */
public class ForkJoinTest {

    @Test
    public void test() {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1, 100);
//        Integer invoke = pool.invoke(task);
        ForkJoinTask<Integer> submit = pool.submit(task);


        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

//        pool.isTerminated();
//        pool.isShutdown();
        try {
            task.join();
            System.out.printf("Main: ", submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
