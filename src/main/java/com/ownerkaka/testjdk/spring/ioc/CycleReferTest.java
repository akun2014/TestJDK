package com.ownerkaka.testjdk.spring.ioc;

import com.ownerkaka.testjdk.support.service.BarService;
import com.ownerkaka.testjdk.support.service.cyclereference.AService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * @author akun
 * @since 2019-06-12
 */
@Slf4j
public class CycleReferTest {

    @Test
    public void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.service.cyclereference");
        AService aService = applicationContext.getBean(AService.class);
        Object aService1 = applicationContext.getBean("AService");
        assertNotNull(aService);
        assertNotNull(aService1);
    }


    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.service.impl");
        BarService barService = applicationContext.getBean(BarService.class);

        assertNotNull(barService);
    }
}