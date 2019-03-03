package com.gk.proxy.aop.advice.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
@Aspect
public class AnnotationAdvice {


    @Pointcut
    public void p1() {

    }

    @Before("p1()")
    public void before() {

    }

    @After("p1()")
    public void after() {

    }

    @AfterReturning
    public void afterReturning() {

    }

    @AfterThrowing
    public void afterThrowing() {

    }

    public void test() {

    }
}
