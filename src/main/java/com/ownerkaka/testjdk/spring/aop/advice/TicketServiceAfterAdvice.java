package com.ownerkaka.testjdk.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterAdvice;
import org.springframework.stereotype.Service;

/**
 * Created by akun on 2018/12/20.
 */
@Service
@Slf4j
public class TicketServiceAfterAdvice implements AfterAdvice, MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } finally {
            log.info("AFTER_ADVICE: 欢迎下次光临代售点....");
        }
    }
}
