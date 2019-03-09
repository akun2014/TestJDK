package com.gk.proxy;

import com.gk.spring.aop.advice.TicketServiceBeforeAdvice;
import com.gk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by akun on 2019/3/6.
 */
@Slf4j
public class ProxyFactoryTest {


    /**
     * ProxyFactory provides a simple way of obtaining and configuring
     * AOP proxies in code.
     */
    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Bar());
        proxyFactory.addAdvice(new TicketServiceBeforeAdvice());

        Bar bar = (Bar) proxyFactory.getProxy();
        bar.bar();
    }
}
