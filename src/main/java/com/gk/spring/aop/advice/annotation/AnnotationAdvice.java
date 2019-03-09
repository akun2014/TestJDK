package com.gk.spring.aop.advice.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class AnnotationAdvice {


    @Pointcut("execution(public * com.gk.support..*.bar(..))")
    public void p1() {

    }

    @Before("p1()")
    public void before(JoinPoint joinPoint) {
        log.info("args:{}", Arrays.toString(joinPoint.getArgs()));
        log.info("before method invoke");
    }

    @After("p1()")
    public void after(JoinPoint joinPoint) {
        log.info("after method invoke");
    }

    @AfterReturning("p1()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("afterReturning method invoke");
    }

    @AfterThrowing("p1()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("afterThrowing method invoke");
    }

    @Around("p1()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        log.info("{}", Arrays.toString(args));
        log.info("around method invoke do");
        Object result = point.proceed();
        log.info("around method invoke done. result:{}", result);
        return "around:" + result;
    }
}
