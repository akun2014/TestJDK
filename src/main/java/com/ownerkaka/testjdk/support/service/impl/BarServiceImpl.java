package com.ownerkaka.testjdk.support.service.impl;

import com.ownerkaka.testjdk.spring.aop.advice.annotation.MyPointCutAnnotation;
import com.ownerkaka.testjdk.support.service.AbstractBaseService;
import com.ownerkaka.testjdk.support.service.BarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * Created by akun on 2019/3/6.
 */
@Slf4j
@Service
public class BarServiceImpl extends AbstractBaseService implements BarService {

    @Override
    public void bar() {
        log.info("method invoked. name:bar()");
        //expose-proxy="true"
        BarService barService = (BarService) AopContext.currentProxy();
        barService.bar("inner invoke");
    }

    @Override
    public void bar(String bar) {
        log.info("method invoked. name:bar(String) {}", bar);
    }

    @Override
    @MyPointCutAnnotation
    public void baseBar(Long num) {
        super.baseBar(num);
    }
}
