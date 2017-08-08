package com.gk.forkJoinFrame;

import java.util.concurrent.RecursiveTask;

/**
 * Created by akun on 2017/8/9.
 */
public class CountTask extends RecursiveTask<Integer>{

    private static final int THRESHOLD = 2;
    private  int start;
    private int end;

    public CountTask(int start, int end) {
        this.end = end;
        this.start = start;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean flag = (end - start) <= THRESHOLD;
        if (flag) {
            System.out.println("直接计算，start:" + start + " end:" + end);
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            System.out.println("新线程计算，start:" + start + " end:" + end);
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
