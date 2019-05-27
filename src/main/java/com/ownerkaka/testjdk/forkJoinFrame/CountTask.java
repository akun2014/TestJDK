package com.ownerkaka.testjdk.forkJoinFrame;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by akun on 2017/8/9.
 */
public class CountTask extends RecursiveTask<Integer>{

    private static final int THRESHOLD = 5;
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
            try {
                TimeUnit.MILLISECONDS.sleep(Integer.parseInt(RandomStringUtils.randomNumeric(1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            System.out.println("新线程计算，start:" + start + " end:" + end);
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            invokeAll(leftTask, rightTask);
//            leftTask.fork();
//            rightTask.fork();
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
