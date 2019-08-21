package com.ownerkaka.testjdk.spring.aop.advice;

import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.*;

import java.lang.reflect.Method;

/**
 * @author akun
 * @since 2019-06-26
 */
@Slf4j
public class AdviceTest {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    private SimpleAspectInstanceFactory aspectInstanceFactory = new SimpleAspectInstanceFactory(Bar.class);
    private Method method = Bar.class.getMethod("afterPropertiesSet");

    public AdviceTest() throws NoSuchMethodException {
    }

    public void testAspectJAfterAdvice() {
        AspectJAfterAdvice afterAdvice = new AspectJAfterAdvice(method, pointcut, aspectInstanceFactory);
    }

    public void testAspectJAfterReturningAdvice() {
        AspectJAfterReturningAdvice afterReturningAdvice = new AspectJAfterReturningAdvice(method, pointcut, aspectInstanceFactory);
    }

    public void testAspectJMethodBeforeAdvice() {
        AspectJMethodBeforeAdvice methodBeforeAdvice = new AspectJMethodBeforeAdvice(method, pointcut, aspectInstanceFactory);
    }

    public void testAspectJAfterThrowingAdvice() {
        AspectJAfterThrowingAdvice afterThrowingAdvice = new AspectJAfterThrowingAdvice(method, pointcut, aspectInstanceFactory);
    }

    public void testAspectJAroundAdvice() {
        AspectJAroundAdvice aroundAdvice = new AspectJAroundAdvice(method, pointcut, aspectInstanceFactory);
    }
}