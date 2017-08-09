package com.gk.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * Created by akun on 2017/8/9.
 * Thread 中start run的区别
 * start method description:Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
 * The result is that two threads are running concurrently: the current thread
 * (which returns from the call to the start method) and the other thread (which executes its run method).
 * <p>
 * run method description:If this thread was constructed using a separate Runnable run object,
 * then that Runnable object's run method is called; otherwise, this method does nothing and returns.
 */
public class ThreadTest {

    static class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("mythread running");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mythread run finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);

        thread.start();
//        thread.run();
//        TimeUnit.SECONDS.sleep(1);
//        thread.join();
        System.out.println("main thread running");

        //if use run method, console print order must
//        mythread running
//        mythread run finished
//        main thread running

        //if use start method, then console print order is
//        mythread running
//        main thread running
//        mythread run finished

    }
}
