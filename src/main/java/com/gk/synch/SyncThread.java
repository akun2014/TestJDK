package com.gk.synch;

/**
 * Created by akun on 2017/7/6.
 */
public class SyncThread implements Runnable{
    private static int count;

    public SyncThread() {
        count = 0;
    }

    public  void run() {
        synchronized(this) {
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

    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread1, "thread1");
        Thread thread2 = new Thread(syncThread2, "thread2");
        thread1.start();
        thread2.start();
    }
    public static void test2() {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "thread1");
        Thread thread2 = new Thread(syncThread, "thread2");
        thread1.start();
        thread2.start();
    }
}
