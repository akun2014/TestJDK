package com.ownerkaka.testjdk.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class MethodInterceptorTest implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("clazz:{} invoked", this.getClass().getCanonicalName());
        return proxy.invokeSuper(obj, args);
    }
}
