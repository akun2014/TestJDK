package com.ownerkaka.testjdk.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * Created by akun on 2018/5/6.
 */
public class DeadLockTest {

    private static final String lock1 = "lock1";
    private static final String lock2 = "lock2";

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (InterruptedException ignore) {
                }
                System.out.println("got lock1");
                synchronized (lock2) {
                    System.out.println("got lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (InterruptedException ignore) {
                }
                System.out.println("got lock2");
                synchronized (lock1) {
                    System.out.println("got lock1");
                }
            }
        });

        thread1.start();
        thread2.start();

    }


}
