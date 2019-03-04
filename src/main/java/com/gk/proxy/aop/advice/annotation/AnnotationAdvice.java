package com.gk.proxy.aop.advice.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
@Aspect
public class AnnotationAdvice {


    @Pointcut("execution(public * com.gk.bean..*.bar(..))")
    public void p1() {

    }

    @Before("p1()")
    public void before() {
        log.info("before method invoke");
    }

    @After("p1()")
    public void after() {
        log.info("after method invoke");
    }

    @AfterReturning
    public void afterReturning() {
        log.info("afterReturning method invoke");
    }

    @AfterThrowing
    public void afterThrowing() {
        log.info("afterThrowing method invoke");
    }

    @Around("p1()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("around method invoke do");
        Object result = point.proceed();
        log.info("around method invoke done");
        return result;
    }
}
