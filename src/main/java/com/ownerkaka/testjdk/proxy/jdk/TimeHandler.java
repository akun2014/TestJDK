package com.ownerkaka.testjdk.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object obj) {
        super();
        target = obj;
    }

    /**
     * proxy jdk生成的代理对象
     * method 代理方法
     * args 方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        log.info("日志记录开始");
        long s = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long e = System.currentTimeMillis();
        log.info("日志记录结束");
        log.info("运行时间:{}", e - s);
        return result;
    }

}
