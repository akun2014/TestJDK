package com.ownerkaka.testjdk.designpattern23.visitor;

import com.alibaba.fastjson.JSON;
import com.ownerkaka.testjdk.support.bean.Foo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.util.StringValueResolver;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
public class BeanDefinitionVisitorTest {

    @Test
    public void testBeanDefinitionVisitor() {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition()
                .setLazyInit(true)
                .setInitMethodName("init")
                .setDestroyMethodName("destroy")
                .setAbstract(false)
                .getBeanDefinition();
        beanDefinition.setBeanClass(Foo.class);

        StringValueResolver valueResolver = strVal -> {
            if (strVal.length() > 0) {
                log.info("visited:{}", strVal);
            }
            return strVal;
        };

        BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(valueResolver);
        visitor.visitBeanDefinition(beanDefinition);
        log.info("beanDefinition:{}", JSON.toJSONString(beanDefinition, true));
    }
}