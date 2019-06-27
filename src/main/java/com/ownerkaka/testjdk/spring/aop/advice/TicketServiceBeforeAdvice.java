package com.ownerkaka.testjdk.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by akun on 2018/12/20.
 */
@Slf4j
public class TicketServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("BEFORE_ADVICE: 欢迎光临代售点....");
    }
}
