package com.ownerkaka.testjdk.spring.aop;

import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceAfterReturningAdvice;
import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceBeforeAdvice;
import com.ownerkaka.testjdk.support.bean.Bar;
import com.ownerkaka.testjdk.support.service.BarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by akun on 2019/3/5.
 */
@Slf4j
public class ProxyFactoryBeanTest {


    @Test
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/boyu/IdeaProjects/TestJDK");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gk.support.bean");
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();

        proxyFactoryBean.setBeanFactory(applicationContext);
        proxyFactoryBean.setTargetName("bar");
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setSingleton(true);

        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        aspectJExpressionPointcutAdvisor.setAdvice(new TicketServiceBeforeAdvice());
        aspectJExpressionPointcutAdvisor.setExpression("execution(public * com.gk.bean..*.bar(..))");

        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor1 = new AspectJExpressionPointcutAdvisor();
        aspectJExpressionPointcutAdvisor1.setAdvice(new TicketServiceAfterReturningAdvice());
        aspectJExpressionPointcutAdvisor1.setExpression("execution(public * com.gk.bean..*.setBeanName(..))");

        proxyFactoryBean.addAdvisors(aspectJExpressionPointcutAdvisor, aspectJExpressionPointcutAdvisor1);
        Bar bar = (Bar) proxyFactoryBean.getObject();
        bar.bar();
        bar.setBeanName("bar");
    }

    @Test
    public void testByAnnotation() {
        String[] packages = new String[]{"com.gk.bean", "com.gk.proxy.aop.advice.annotation"};
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(packages);

        Bar bean = applicationContext.getBean(Bar.class);
        bean.bar();
    }

    @Test
    public void testInterface() throws Exception {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/boyu/IdeaProjects/TestJDK");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gk.support", "com.gk.spring.aop.advice");
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
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/boyu/IdeaProjects/TestJDK");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gk.support.service", "com.gk.spring.aop.advice.annotation");

        BarService barService = applicationContext.getBean(BarService.class);
        barService.bar("test");
    }
}
