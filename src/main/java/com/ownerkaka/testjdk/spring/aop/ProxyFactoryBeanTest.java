package com.ownerkaka.testjdk.spring.aop;

import com.ownerkaka.testjdk.spring.ConfigurationTest;
import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceAfterReturningAdvice;
import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceBeforeAdvice;
import com.ownerkaka.testjdk.spring.aop.advice.annotation.AnnotationAdvice;
import com.ownerkaka.testjdk.spring.ioc.processor.BeanFactoryPostProcessorTest;
import com.ownerkaka.testjdk.spring.ioc.processor.BeanPostProcessorTest;
import com.ownerkaka.testjdk.support.bean.Bar;
import com.ownerkaka.testjdk.support.bean.Foo;
import com.ownerkaka.testjdk.support.service.AbstractBaseService;
import com.ownerkaka.testjdk.support.service.BarService;
import com.ownerkaka.testjdk.support.service.impl.BarServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * Created by akun on 2019/3/5.
 */
@Slf4j
public class ProxyFactoryBeanTest {


    @Test
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/tmp");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.bean");
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();

        proxyFactoryBean.setBeanFactory(applicationContext);
        proxyFactoryBean.setTargetName("bar");
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setSingleton(true);

        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        aspectJExpressionPointcutAdvisor.setAdvice(new TicketServiceBeforeAdvice());
        aspectJExpressionPointcutAdvisor.setExpression("execution(public * com.ownerkaka.testjdk.bean..*.bar(..))");

        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor1 = new AspectJExpressionPointcutAdvisor();
        aspectJExpressionPointcutAdvisor1.setAdvice(new TicketServiceAfterReturningAdvice());
        aspectJExpressionPointcutAdvisor1.setExpression("execution(public * com.ownerkaka.testjdk.bean..*.setBeanName(..))");

        proxyFactoryBean.addAdvisors(aspectJExpressionPointcutAdvisor, aspectJExpressionPointcutAdvisor1);
        Bar bar = (Bar) proxyFactoryBean.getObject();
        bar.bar();
        bar.setBeanName("bar");
    }

    @Test
    public void testByAnnotation() {
        Class[] clz = new Class[]{BeanFactoryPostProcessorTest.class, BeanPostProcessorTest.class,
                Bar.class, AnnotationAdvice.class, Foo.class};
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(clz);

        Bar bean = applicationContext.getBean(Bar.class);
        bean.bar();
    }

    @Test
    public void testByAnnotation1() {
        Class[] clz = new Class[]{BarServiceImpl.class, ConfigurationTest.class,
                Bar.class, AnnotationAdvice.class, Foo.class};
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(clz);

        AbstractBaseService barService = (AbstractBaseService) applicationContext.getBean(BarService.class);
        barService.baseBar(1L);
        Map<String, AbstractBaseService> beansOfType = applicationContext.getBeansOfType(AbstractBaseService.class);
        int size = beansOfType.size();
        log.info("{}", size);
    }

    @Test
    public void testInterface() throws Exception {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/tmp");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support", "com.ownerkaka.testjdk.spring.aop.advice");
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setBeanFactory(applicationContext);

        proxyFactoryBean.setTargetName("barServiceImpl");
        proxyFactoryBean.setProxyTargetClass(false);
        proxyFactoryBean.setInterceptorNames("ticketServiceAfterReturningAdvice");

        BarService barService = (BarService) proxyFactoryBean.getObject();
        barService.bar();
        barService.bar("test");
    }

    @Test
    public void testApp() throws Exception {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/tmp");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.service", "com.ownerkaka.testjdk.spring.aop.advice.annotation");

        BarService barService = applicationContext.getBean(BarService.class);
        barService.bar("test");
    }
}
