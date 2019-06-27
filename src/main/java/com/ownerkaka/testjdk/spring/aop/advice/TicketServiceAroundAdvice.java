package com.ownerkaka.testjdk.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by akun on 2018/12/20.
 */
@Slf4j
public class TicketServiceAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("AROUND_ADVICE:BEGIN...");
        Object returnValue = methodInvocation.proceed();
        log.info("AROUND_ADVICE:END.....");
        return returnValue;
    }
}
