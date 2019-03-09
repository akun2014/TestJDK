package com.gk.spring.aop;

import com.gk.bean.Bar;
import com.gk.proxy.aop.advice.TicketServiceAfterReturningAdvice;
import com.gk.proxy.aop.advice.TicketServiceBeforeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by akun on 2019/3/5.
 */
@Slf4j
public class ProxyFactoryBeanTest {


    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gk.bean");
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
}
