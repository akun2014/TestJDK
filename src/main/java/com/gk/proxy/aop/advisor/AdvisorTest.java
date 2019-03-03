package com.gk.proxy.aop.advisor;

import com.gk.bean.Bar;
import com.gk.proxy.aop.advice.TicketServiceAfterReturningAdvice;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.After;
import org.junit.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class AdvisorTest {

    private final String expression = "execution( * sellTicket(..))";

    private Pointcut pointcut;
    private Advice advice;

    @Test
    public void testRegexpMethodPointcutAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setPattern("test");
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());

        pointcut = advisor.getPointcut(); //org.springframework.aop.support.JdkRegexpMethodPointcut
        advice = advisor.getAdvice();

    }

    @Test
    public void testAspectJExpressionPointcutAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());

        pointcut = advisor.getPointcut();//org.springframework.aop.aspectj.AspectJExpressionPointcut
        advice = advisor.getAdvice();
    }

    @Test
    public void testNameMatchMethodPointcutAdvisor() {
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());

        pointcut = advisor.getPointcut();//org.springframework.aop.support.NameMatchMethodPointcut
        advice = advisor.getAdvice();
    }

    @Test
    public void testDefaultPointAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();

        pointcut = advisor.getPointcut();
        advice = advisor.getAdvice();
    }

    @Test
    public void testDefaultBeanFactoryPointcutAdvisor() throws NoSuchMethodException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gk.bean");
        DefaultBeanFactoryPointcutAdvisor advisor = new DefaultBeanFactoryPointcutAdvisor();

        advisor.setPointcut(new NameMatchMethodPointcut());
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());
        advisor.setBeanFactory(applicationContext);
        advisor.setAdviceBeanName("bar");

        pointcut = advisor.getPointcut();
        advice = advisor.getAdvice();

        Method method = Bar.class.getMethod("afterPropertiesSet");
        pointcut.getMethodMatcher().matches(method, Bar.class);
    }

    @After
    public void after() {
        log.info("pointcut:{} advice:{}", pointcut.getClass().getCanonicalName(), advice.getClass().getCanonicalName());
    }
}
