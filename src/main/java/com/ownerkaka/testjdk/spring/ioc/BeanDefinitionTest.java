package com.ownerkaka.testjdk.spring.ioc;

import com.ownerkaka.testjdk.spring.IOCBase;
import com.ownerkaka.testjdk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.springframework.util.Assert.notNull;

/**
 * Created by akun on 2019/2/22.
 *
 * @see RootBeanDefinition
 * @see ChildBeanDefinition
 * @see GenericBeanDefinition
 * @see ConfigurationClassBeanDefinition
 * @see ScannedGenericBeanDefinition
 */
@Slf4j
public class BeanDefinitionTest extends IOCBase {

    @Test
    public void testRootBeanDefinition() {

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("name", "gk");
        applicationContext.registerBeanDefinition("user", beanDefinition);

        applicationContext.refresh();
        User user = (User) applicationContext.getBean("user");
        notNull(user, "bean may not be null");
    }

    @Test
    public void testChildBeanDefinition() {
        ChildBeanDefinition beanDefinition = new ChildBeanDefinition("user");
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("name", "gk");
        applicationContext.registerBeanDefinition("user", beanDefinition);

        applicationContext.refresh();
        User user = (User) applicationContext.getBean("user");
        notNull(user, "bean may not be null");
    }

    @Test
    public void testGenericBeanDefinition() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("name", "gk");
        applicationContext.registerBeanDefinition("user", beanDefinition);

        applicationContext.refresh();
        User user = (User) applicationContext.getBean("user");
        notNull(user, "bean may not be null");
    }

    /**
     * 使用xml加载的bean会解析为 GenericBeanDefinition
     */
    @Test
    public void testGenericBeanDefinition2() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application-customer.xml");

        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("user");
        boolean equals = beanDefinition.getClass().equals(GenericBeanDefinition.class);
        Assert.assertTrue(equals);

    }

    /**
     * 以 @Bean 注解标记的会解析为 ConfigurationClassBeanDefinition
     */
    @Test
    public void testConfigurationClassBeanDefinition() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.service");

        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("human");
        boolean equals = beanDefinition.getClass().getSimpleName().equals("ConfigurationClassBeanDefinition");
        Assert.assertTrue(equals);

    }

    /**
     * 以 @Component @Configuration 注解标记的会解析为 ScannedGenericBeanDefinition
     */
    @Test
    public void testScannedGenericBeanDefinition() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.service");

        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("customConfigService");
        boolean equals = beanDefinition.getClass().equals(ScannedGenericBeanDefinition.class);
        Assert.assertTrue(equals);
    }

    @Test
    public void testBeanDefinitionBuilder() {

        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(User.class);
        definitionBuilder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        definitionBuilder.addPropertyReference("name", "gk");
        definitionBuilder.setAbstract(false);
        definitionBuilder.setLazyInit(false);

        RootBeanDefinition rbd = (RootBeanDefinition) definitionBuilder.getBeanDefinition();

    }

}
