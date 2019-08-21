package com.ownerkaka.testjdk.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;

/**
 * Created by akun on 2018/12/20.
 */
@Slf4j
public class TicketServiceAfterThrowingAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception ex) {
        log.info("after throwing...");
    }
}
