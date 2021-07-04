package com.ownerkaka.testjdk.spring.aop;

import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@Slf4j
public class AnnotationAopTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(
                "com.ownerkaka.testjdk.support.bean",
                "com.ownerkaka.testjdk.spring.ioc", "com.ownerkaka.testjdk.spring.aop.advice.annotation");
        applicationContext.setAllowCircularReferences(true);
        Bar bar = applicationContext.getBean(Bar.class);
        log.info("bar:{}", bar.getClass());

    }
}
