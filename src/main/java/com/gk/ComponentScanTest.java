package com.gk;

import com.gk.bean.Bar;
import com.gk.bean.Person;
import com.gk.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by akun on 2019/2/28.
 */
@Slf4j
public class ComponentScanTest {
    @Test
    public void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gk.bean");
        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("bar");
        log.info("clazz:{}", beanDefinition.getClass().getCanonicalName()); //ScannedGenericBeanDefinition
        log.info("{}", beanDefinition);

        Bar bar = (Bar) applicationContext.getBean("bar");
        System.out.println(bar.toString());
    }

    @Test
    public void test() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("application-annotation.xml");
        applicationContext.refresh();

        Bar bar = (Bar) applicationContext.getBean("bar");
        User user = (User) applicationContext.getBean("user");
        Person person = (Person) applicationContext.getBean("person");

        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("bar");
        BeanDefinition userBeanDefinition = applicationContext.getBeanFactory().getBeanDefinition("user");
        BeanDefinition personBeanDefinition = applicationContext.getBeanFactory().getBeanDefinition("person");
        BeanDefinition configurationTestBeanDefinition = applicationContext.getBeanFactory().getBeanDefinition("configurationTest");

        log.info("bar beanDefinition:{}", beanDefinition.getClass().getCanonicalName()); //ScannedGenericBeanDefinition
        log.info("user beanDefinition:{}", userBeanDefinition.getClass().getCanonicalName()); //GenericBeanDefinition
        log.info("person beanDefinition:{}", personBeanDefinition.getClass().getCanonicalName()); //GenericBeanDefinition
        log.info("configurationTestBeanDefinition beanDefinition:{}", configurationTestBeanDefinition.getClass().getCanonicalName()); //GenericBeanDefinition


        System.out.println(bar.toString());
        System.out.println(user.toString());
        System.out.println(person.toString());

    }


}
