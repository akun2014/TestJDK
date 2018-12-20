package com.gk.proxy.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by akun on 2018/12/20.
 */
public class TicketServiceAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("AROUND_ADVICE:BEGIN....");
        Object returnValue = methodInvocation.proceed();
        System.out.println("AROUND_ADVICE:END.....");
        return returnValue;
    }
}
