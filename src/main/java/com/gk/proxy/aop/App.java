package com.gk.proxy.aop;

import com.gk.spring.aop.advice.TicketServiceAfterReturningAdvice;
import com.gk.spring.aop.advice.TicketServiceAroundAdvice;
import com.gk.spring.aop.advice.TicketServiceBeforeAdvice;
import com.gk.spring.aop.advice.TicketServiceThrowsAdvice;
import net.sf.cglib.core.DebuggingClassWriter;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactoryBean;

/**
 * Created by akun on 2018/12/20.
 * ---------------------
 * 作者：亦山
 * 原文：https://blog.csdn.net/luanlouis/article/details/51155821
 */
public class App {
    public static void main(String[] args) {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/boyu/IdeaProjects/TestJDK");
        //1.针对不同的时期类型，提供不同的Advice
        Advice beforeAdvice = new TicketServiceBeforeAdvice();
        Advice afterReturningAdvice = new TicketServiceAfterReturningAdvice();
        Advice aroundAdvice = new TicketServiceAroundAdvice();
        Advice throwsAdvice = new TicketServiceThrowsAdvice();

        RailwayStation railwayStation = new RailwayStation();

        //2.创建ProxyFactoryBean,用以创建指定对象的Proxy对象
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //3.设置Proxy的接口
        proxyFactoryBean.setInterfaces(TicketService.class);
        //4. 设置RealSubject
        proxyFactoryBean.setTarget(railwayStation);
        //5.使用JDK基于接口实现机制的动态代理生成Proxy代理对象，如果想使用CGLIB，需要将这个flag设置成true
        proxyFactoryBean.setProxyTargetClass(true);

        //6. 添加不同的Advice
        proxyFactoryBean.addAdvice(afterReturningAdvice);
        proxyFactoryBean.addAdvice(aroundAdvice);
        proxyFactoryBean.addAdvice(throwsAdvice);
        proxyFactoryBean.addAdvice(beforeAdvice);
        //7通过ProxyFactoryBean生成Proxy对象
        TicketService ticketService = (TicketService) proxyFactoryBean.getObject();
        ticketService.sellTicket();
    }
}
