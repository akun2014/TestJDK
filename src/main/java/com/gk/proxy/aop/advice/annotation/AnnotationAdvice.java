package com.gk.proxy.aop.advice.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Component;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
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

    @AfterReturning("p1()")
    public void afterReturning() {
        log.info("afterReturning method invoke");
    }

    @AfterThrowing("p1()")
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
