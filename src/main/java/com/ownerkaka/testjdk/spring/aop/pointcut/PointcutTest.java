package com.ownerkaka.testjdk.spring.aop.pointcut;

import com.ownerkaka.testjdk.support.bean.Bar;
import com.ownerkaka.testjdk.spring.aop.advice.annotation.AnnotationAdvice;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class PointcutTest {

    private final String expression = "execution( * bara(..))";

    ApplicationContext applicationContext;
    MethodMatcher methodMatcher;

    @Before
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext("com.gk.support.bean");
    }

    /**
     * 表达式声明pointcut
     */
    @Test
    public void test() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setBeanFactory(applicationContext);
        pointcut.setExpression(expression);

        methodMatcher = pointcut.getMethodMatcher();
        Method method = Bar.class.getMethod("bar");
        assertTrue(pointcut.matches(method, Bar.class));
    }

    /**
     * 名称匹配
     */
    @Test
    public void testNameMatchMethodPointcut() throws NoSuchMethodException {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("afterPropertiesSet");

        Method method = Bar.class.getMethod("afterPropertiesSet");
        assertTrue(pointcut.matches(method, Bar.class));
    }

    /**
     * 注解式声明
     */
    @Test
    public void testAnnotationMatchingPointcut() throws NoSuchMethodException {
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Aspect.class, org.aspectj.lang.annotation.Before.class);
        Method before = AnnotationAdvice.class.getMethod("before");

        methodMatcher = pointcut.getMethodMatcher();
        assertTrue(methodMatcher.matches(before, AnnotationAdvice.class));
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
