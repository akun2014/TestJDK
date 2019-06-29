package com.ownerkaka.testjdk.designpattern23.budiler;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author akun
 * @since 2019-06-28
 */
@Slf4j
public class BuilderTests {
    @Test
    public void testBeanDefinition() {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition()
                .setLazyInit(true)
                .setInitMethodName("init")
                .setDestroyMethodName("destroy")
                .setAbstract(false)
                .getBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        assertTrue(beanDefinition.isLazyInit());
    }

    @Test
    public void testLombokBuilderAnnotation() {
        BeanByLombok build = BeanByLombok.builder()
                .name("testjdk")
                .num(2)
                .created(DateTime.now().toDate())
                .build();
        assertEquals(build.getName(), "testjdk");
    }

    @Test
    public void testUserBuilder() {
        User user = User.UserBuilder.builder()
                .setName("testjdk")
                .setNum(2)
                .setCreated(new Date())
                .build();
        assertEquals(user.getNum().longValue(), 2);
    }
}