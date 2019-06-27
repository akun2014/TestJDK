package com.ownerkaka.testjdk.proxy;

import com.ownerkaka.testjdk.proxy.jdk.Car;
import com.ownerkaka.testjdk.proxy.jdk.Moveable;
import com.ownerkaka.testjdk.spring.aop.advice.*;
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
    public void test() throws Exception {
        // create proxy factory
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Car());
        proxyFactory.setProxyTargetClass(false);
        proxyFactory.setExposeProxy(false);
//        proxyFactory.setInterfaces(Moveable.class);
        proxyFactory.setOptimize(false);

        //around advisor
        NameMatchMethodPointcutAdvisor aroundAdvisor = new NameMatchMethodPointcutAdvisor();
        aroundAdvisor.setAdvice(new TicketServiceAroundAdvice());
        aroundAdvisor.setMappedName("move");

        //after returning advisor
        DefaultPointcutAdvisor afterReturningAdvisor = new DefaultPointcutAdvisor();
        afterReturningAdvisor.setAdvice(new TicketServiceAfterReturningAdvice());

        //throw advisor
        NameMatchMethodPointcutAdvisor throwAdvisor = new NameMatchMethodPointcutAdvisor();
        throwAdvisor.setAdvice(new TicketServiceAfterThrowingAdvice());
        throwAdvisor.setMappedName("move");

        //set to proxy factory
        proxyFactory.addAdvice(new TicketServiceBeforeAdvice());
        proxyFactory.addAdvice(new TicketServiceAfterAdvice());
        proxyFactory.addAdvisor(aroundAdvisor);
        proxyFactory.addAdvisor(afterReturningAdvisor);
        proxyFactory.addAdvisor(throwAdvisor);

        System.out.println(proxyFactory.toProxyConfigString());

        //get proxy
        Moveable moveable = (Moveable) proxyFactory.getProxy();
        moveable.move();
    }
}
