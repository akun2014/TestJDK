package com.gk.jvm;

/**
 * Created by akun on 2018/4/25.
 */
public class VolatileTest {

    static volatile int race = 0;
    static final int loopCount = 10000;
    static final int threadNum = 20;

    public static void increase() {
        race++;//这里不是原子操作，会有并发操作会有线程安全问题
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < loopCount; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        System.out.println("======");

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
