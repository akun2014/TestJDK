package com.gk.forkJoinFrame;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by akun on 2017/8/9.
 */
public class ForkJoinTest {

    @Test
    public void test() {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1, 4);
        ForkJoinTask<Integer> submit = pool.submit(task);

        try {

            System.out.println(submit.get());
        } catch (Exception e) {

        }

    }
}
