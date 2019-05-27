package com.ownerkaka.testjdk.synch;

/**
 * Created by akun on 2017/7/6.
 */
public class Synchronized implements Runnable {
    private static int count;

    public Synchronized() {
        count = 0;
    }

    public void run() {
       addSyncForObject("A");
       printCountCommon();

    }

    public void addForClaz() {
        synchronized (Synchronized.class) {//类级别锁
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addSyncForObject() {
        synchronized (this) {//当前对象锁
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void addSyncForObject(String type) {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void printCount(String type) {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void printCount() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printCountCommon() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        Synchronized syncThread1 = new Synchronized();
        Synchronized syncThread2 = new Synchronized();
        Thread thread1 = new Thread(syncThread1, "thread1");
        Thread thread2 = new Thread(syncThread2, "thread2");
        thread1.start();
        thread2.start();
    }

    public static void test2() {
        Synchronized syncThread = new Synchronized();
        Thread thread1 = new Thread(syncThread, "thread1");
        Thread thread2 = new Thread(syncThread, "thread2");
        thread1.start();
        thread2.start();
    }


}
