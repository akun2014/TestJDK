package com.ownerkaka.testjdk.spring.aop.advisor;

import com.ownerkaka.testjdk.spring.aop.advice.TicketServiceAfterReturningAdvice;
import com.ownerkaka.testjdk.spring.aop.advice.annotation.AnnotationAdvice;
import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.aspectj.annotation.SimpleMetadataAwareAspectInstanceFactory;
import org.springframework.aop.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class AdvisorTest {

    private final String expression = "execution( * bar(..))";

    @Test
    public void testRegexpMethodPointcutAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setPattern("test");
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());

        assertTrue(advisor.getPointcut() instanceof JdkRegexpMethodPointcut);
        assertTrue(advisor.getAdvice() instanceof TicketServiceAfterReturningAdvice);
        assertEquals(advisor.getPointcut().getClassFilter().getClass().getSimpleName(), "TrueClassFilter");
        assertTrue(advisor.getPointcut().getMethodMatcher() instanceof JdkRegexpMethodPointcut);
    }

    @Test
    public void testAspectJExpressionPointcutAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());

        assertTrue(advisor.getPointcut() instanceof AspectJExpressionPointcut);
        assertTrue(advisor.getAdvice() instanceof TicketServiceAfterReturningAdvice);
        assertTrue(advisor.getPointcut().getClassFilter() instanceof AspectJExpressionPointcut);
        assertTrue(advisor.getPointcut().getMethodMatcher() instanceof AspectJExpressionPointcut);
    }

    @Test
    public void testAspectJPointcutAdvisor() throws NoSuchMethodException {
        AspectJAfterAdvice afterAdvice = new AspectJAfterAdvice(
                Bar.class.getDeclaredMethod("bar"),
                new AspectJExpressionPointcut(),
                new SimpleMetadataAwareAspectInstanceFactory(AnnotationAdvice.class, "annotationAdvice"));
        afterAdvice.getPointcut().setExpression("execution(public * com.ownerkaka.testjdk.support..*.bar(..))");
        AspectJPointcutAdvisor advisor = new AspectJPointcutAdvisor(afterAdvice);

        assertTrue(advisor.getPointcut() instanceof ComposablePointcut);
        assertTrue(advisor.getAdvice() instanceof AspectJAfterAdvice);
        assertTrue(advisor.getPointcut().getClassFilter() instanceof AspectJExpressionPointcut);
        assertEquals(advisor.getPointcut().getMethodMatcher().getClass().getSimpleName(), "IntersectionIntroductionAwareMethodMatcher");
    }

    @Test
    public void testNameMatchMethodPointcutAdvisor() {
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());

        assertTrue(advisor.getPointcut() instanceof NameMatchMethodPointcut);
        assertTrue(advisor.getAdvice() instanceof TicketServiceAfterReturningAdvice);
        assertEquals(advisor.getPointcut().getClassFilter().getClass().getSimpleName(), "TrueClassFilter");
        assertTrue(advisor.getPointcut().getMethodMatcher() instanceof NameMatchMethodPointcut);
    }

    @Test
    public void testDefaultPointAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());

        assertEquals(advisor.getPointcut().getClass().getSimpleName(), "TruePointcut");
        assertTrue(advisor.getAdvice() instanceof TicketServiceAfterReturningAdvice);
        assertEquals(advisor.getPointcut().getClassFilter().getClass().getSimpleName(), "TrueClassFilter");
        assertEquals(advisor.getPointcut().getMethodMatcher().getClass().getSimpleName(), "TrueMethodMatcher");
    }

    @Test
    public void testDefaultBeanFactoryPointcutAdvisor() throws NoSuchMethodException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.bean");
        DefaultBeanFactoryPointcutAdvisor advisor = new DefaultBeanFactoryPointcutAdvisor();

        advisor.setPointcut(new NameMatchMethodPointcut());
        advisor.setAdvice(new TicketServiceAfterReturningAdvice());
        advisor.setBeanFactory(applicationContext);
        advisor.setAdviceBeanName("bar");

        assertTrue(advisor.getPointcut() instanceof NameMatchMethodPointcut);
        assertTrue(advisor.getAdvice() instanceof TicketServiceAfterReturningAdvice);

        Method method = Bar.class.getMethod("afterPropertiesSet");
        advisor.getPointcut().getMethodMatcher().matches(method, Bar.class);
        advisor.getPointcut().getClassFilter().matches(Bar.class);
    }
}
