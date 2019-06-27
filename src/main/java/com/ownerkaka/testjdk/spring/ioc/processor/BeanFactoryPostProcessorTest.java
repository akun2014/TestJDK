package com.ownerkaka.testjdk.spring.ioc.processor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by akun on 2019/3/2.
 * 在applicationContext容器支持排序
 * <p>
 * 拓展点
 * <br>
 * placeholder处理的
 */
@Slf4j
@Component
@Order(value = 1)
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        Map<String, String> data = new HashMap<>(16);
        Set<String> beanDefinitionNameSet = new HashSet<>();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            data.put(beanDefinitionName, beanDefinition.getClass().getSimpleName() + "#" + beanClassName);
            beanDefinitionNameSet.add(beanDefinition.getClass().getSimpleName());
        }
        log.info("beanDefinition:{}", JSON.toJSONString(data, true));
        Object[] array = Sets.newHashSet(beanDefinitionNameSet).toArray();
        log.info("beanDefinition:{}", Arrays.toString(array));
    }
}
