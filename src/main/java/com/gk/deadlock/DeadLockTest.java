package com.gk.deadlock;

/**
 * Created by akun on 2018/5/6.
 */
public class DeadLockTest {

    public static void main(String[] args) {

        final String lock1 = "lock1";
        final String lock2 = "lock2";

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock1) {
                    System.out.println("got lock1");
                    synchronized (lock2) {
                        System.out.println("got lock2");
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock2) {
                    System.out.println("got lock2");
                    synchronized (lock1) {
                        System.out.println("got lock1");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

    }


}
