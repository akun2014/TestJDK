package com.gk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
        System.out.println("日志记录开始");
        long s = System.currentTimeMillis();
        method.invoke(target, args);
        long e = System.currentTimeMillis();
        System.out.println("日志记录结束");
        System.out.println("运行时间" + (e - s));
        return null;
    }

}
