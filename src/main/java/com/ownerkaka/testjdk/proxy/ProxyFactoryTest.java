package com.ownerkaka.testjdk.proxy;

import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceAfterReturningAdvice;
import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceAroundAdvice;
import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceBeforeAdvice;
import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

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

        //customer set advisor
        proxyFactory.addAdvice(new TicketServiceBeforeAdvice());
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new TicketServiceAfterReturningAdvice()));
        proxyFactory.addAdvisor(new NameMatchMethodPointcutAdvisor(new TicketServiceAroundAdvice()));

        System.out.println(proxyFactory.toProxyConfigString());

        Bar bar = (Bar) proxyFactory.getProxy();
        bar.bar();
    }
}
