package com.gk.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class InvokerHandlerTest implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("clazz:{} invoked", this.getClass().getCanonicalName());
        return method.invoke(proxy, args);
    }
}
