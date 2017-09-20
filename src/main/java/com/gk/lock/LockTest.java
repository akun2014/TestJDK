package com.gk.lock;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by akun on 2017/9/20.
 */
public class LockTest {
    private final Lock lock = new ReentrantLock();
    final String FLAG = "FLAG";

    @Before
    public void init() throws InterruptedException {
        synchronized (FLAG) {
            System.out.println("lock init");
            TimeUnit.SECONDS.sleep(4);
        }
        System.out.println("unlock");
    }

    public void tempLockTest() {
        lock.lock();
        try {
            //do something
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void lockTest() throws InterruptedException {
        lock.lock();
        boolean tryLock = lock.tryLock(3, TimeUnit.SECONDS);
        System.out.println("try lock result is:" + tryLock);
        if (tryLock) {
            lock.unlock();
        }
    }
}
