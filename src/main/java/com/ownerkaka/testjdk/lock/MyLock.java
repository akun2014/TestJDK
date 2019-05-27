package com.ownerkaka.testjdk.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by akun on 2017/10/24.
 */
public class MyLock {


    Sync sync;


    public static class Sync extends AbstractQueuedSynchronizer {

        public void lock() {
            super.compareAndSetState(1, 1);
            super.tryAcquire(1);
        }

    }
}

