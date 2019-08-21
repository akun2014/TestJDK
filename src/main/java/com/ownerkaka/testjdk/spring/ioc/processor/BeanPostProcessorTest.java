package com.ownerkaka.testjdk.spring.ioc.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by akun on 2019/2/28.
 * 在applicationContext容器支持排序
 * <p>
 * 拓展点实现
 * 1、回调Aware接口
 * 2、实现AOP（事务管理）
 */
@Slf4j
@Component
@Order(value = 1)
public class BeanPostProcessorTest implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Before-{} bean:{}", beanName, bean.getClass().getSimpleName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("After-{} bean:{}", beanName, bean);
        return bean;
    }
}
