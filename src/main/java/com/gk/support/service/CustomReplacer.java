package com.gk.support.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by akun on 2019/3/24.
 */
@Slf4j
public class CustomReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        log.info("class:{} reimplement method invoked", getClass().getCanonicalName());
        return method.invoke(obj.getClass().newInstance(), args);
    }
}
