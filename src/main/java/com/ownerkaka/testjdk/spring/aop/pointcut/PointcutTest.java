package com.ownerkaka.testjdk.spring.aop.pointcut;

import com.ownerkaka.testjdk.spring.aop.advice.annotation.AnnotationAdvice;
import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class PointcutTest {

    private final String expression = "execution( * bar(..))";
    ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.bean");
    }

    /**
     * 表达式声明pointcut
     */
    @Test
    public void test() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setBeanFactory(applicationContext);
        pointcut.setExpression(expression);

        Method method = Bar.class.getMethod("bar");
        assertSame(pointcut.getClassFilter(), pointcut);
        assertSame(pointcut.getMethodMatcher(), pointcut);
        assertTrue(pointcut.matches(method, Bar.class));
        assertTrue(pointcut.matches(Bar.class));

    }

    /**
     * 名称匹配pointcut
     */
    @Test
    public void testNameMatchMethodPointcut() throws NoSuchMethodException {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("afterPropertiesSet");
        assertEquals(pointcut.getClassFilter().getClass().getSimpleName(), "TrueClassFilter");
        assertSame(pointcut.getMethodMatcher(), pointcut);

        Method method = Bar.class.getMethod("afterPropertiesSet");
        assertTrue(pointcut.matches(method, Bar.class));
    }

    /**
     * 注解式声明pointcut
     */
    @Test
    public void testAnnotationMatchingPointcut() throws NoSuchMethodException {
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Aspect.class, org.aspectj.lang.annotation.Before.class);
        Method beforeMethod = AnnotationAdvice.class.getDeclaredMethod("before", JoinPoint.class);

        assertTrue(pointcut.getClassFilter() instanceof AnnotationClassFilter);
        assertTrue(pointcut.getMethodMatcher() instanceof AnnotationMethodMatcher);
        assertTrue(pointcut.getMethodMatcher().matches(beforeMethod, AnnotationAdvice.class));
    }

    /**
     * 组合式pointcut
     */
    @Test
    public void testComposablePointcut() {
        ComposablePointcut pointcut = new ComposablePointcut();
        pointcut.intersection(new AnnotationMatchingPointcut(Aspect.class, org.aspectj.lang.annotation.Before.class))
                .intersection((org.springframework.aop.Pointcut) new NameMatchMethodPointcut())
                .intersection((MethodMatcher) new NameMatchMethodPointcut());
    }
}
