package com.ownerkaka.testjdk.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Created by akun on 2018/12/20.
 */
@Service
@Slf4j
public class TicketServiceAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("AFTER_RETURNING：本次服务已结束....");
    }
}
