package com.ownerkaka.testjdk.reference;

import com.ownerkaka.testjdk.support.service.BarService;
import com.ownerkaka.testjdk.support.service.impl.BarServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author akun
 * @since 2019-07-26
 */
@Slf4j
public class ReferenceTest {


    @Test
    public void testSoftReference() {
        BarService barService = new BarServiceImpl();
        SoftReference<BarService> softReference = new SoftReference<>(barService);
//        boolean enqueue = softReference.enqueue();
//        boolean enqueued = softReference.isEnqueued();
        BarService barService1 = softReference.get();
        Assert.assertNotNull(barService1);
        softReference.clear();
        Assert.assertNull(softReference.get());
    }

    @Test
    public void testWeakReference() {
        BarService barService = new BarServiceImpl();
        WeakReference<BarService> weakReference = new WeakReference<>(barService);
    }

    @Test
    public void PhantomReference() {
        BarService barService = new BarServiceImpl();
        PhantomReference<BarService> phantomReference = new PhantomReference<>(barService, new ReferenceQueue<>());
        Assert.assertNull(phantomReference.get());
    }
}