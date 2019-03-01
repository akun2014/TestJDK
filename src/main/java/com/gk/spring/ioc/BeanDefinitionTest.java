package com.gk.spring.ioc;

import com.gk.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

/**
 * Created by akun on 2019/2/22.
 *
 * @see RootBeanDefinition
 * @see ChildBeanDefinition
 * @see GenericBeanDefinition
 * @see AnnotatedGenericBeanDefinition
 */
@Slf4j
public class BeanDefinitionTest {

    StaticApplicationContext applicationContext = new StaticApplicationContext();

    @Test
    public void testRootBeanDefinition() {

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("name", "gk");
        applicationContext.registerBeanDefinition("user", beanDefinition);

        applicationContext.refresh();
        User user = (User) applicationContext.getBean("user");
        System.out.println(user.getName());
    }

    @Test
    public void testChildBeanDefinition() {
        ChildBeanDefinition beanDefinition = new ChildBeanDefinition("user");
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("name", "gk");
        applicationContext.registerBeanDefinition("user", beanDefinition);

        applicationContext.refresh();
        User user = (User) applicationContext.getBean("user");
        System.out.println(user.getName());
    }

    @Test
    public void testGenericBeanDefinition() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("name", "gk");
        applicationContext.registerBeanDefinition("user", beanDefinition);

        applicationContext.refresh();
        User user = (User) applicationContext.getBean("user");
        System.out.println(user.getName());
    }

    /**
     * 使用 xml 加载的 bean 也是由 GenericBeanDefinition 来创建
     */
    @Test
    public void testGenericBeanDefinition2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanDefinition.xml");

        User user = (User) applicationContext.getBean("user");
        System.out.println(user.getName());

        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("item");
    }

    /**
     * 以 @Configuration 注解标记的会解析为 AnnotatedGenericBeanDefinition
     */
    @Test
    public void testAnnotatedGenericBeanDefinition() {
        AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(User.class);

        beanDefinition.getPropertyValues().addPropertyValue("name", "gk");
        applicationContext.registerBeanDefinition("user", beanDefinition);

        applicationContext.refresh();
        User user = (User) applicationContext.getBean("user");
        System.out.println(user.getName());

    }

    /**
     * 以 @Bean 注解标记的会解析为 ConfigurationClassBeanDefinition
     */
    @Test
    public void testConfigurationClassBeanDefinition() {

    }

    /**
     * 以 @Component 注解标记的会解析为 ScannedGenericBeanDefinition
     */
    @Test
    public void testScannedGenericBeanDefinition() {
//        ScannedGenericBeanDefinition beanDefinition = new ScannedGenericBeanDefinition(new SimpleMetadataReader());

    }

    @Test
    public void testBeanDefinitionBuilder() {

        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(User.class);
        definitionBuilder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        definitionBuilder.addPropertyReference("name", "gk");

        RootBeanDefinition rbd = (RootBeanDefinition) definitionBuilder.getBeanDefinition();

    }

}
