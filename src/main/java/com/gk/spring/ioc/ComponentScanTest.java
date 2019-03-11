package com.gk.spring.ioc;

import com.gk.support.bean.Bar;
import com.gk.support.bean.Person;
import com.gk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Set;

/**
 * Created by akun on 2019/2/28.
 */
@Slf4j
public class ComponentScanTest {

    @Test
    public void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.gk.support.bean", "com.gk.spring.ioc");
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

    @Test
    public void testClassPathBeanDefinitionScanner() throws Exception {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        ClassPathBeanDefinitionScannerTest scanner = new ClassPathBeanDefinitionScannerTest(applicationContext);
        Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.doScan("com.gk.support.bean");
        Assert.assertEquals(2, beanDefinitionHolders.size());
        // refresh beanFactory
        applicationContext.refresh();
        // after refreshed application can be used.
        Bar bean = applicationContext.getBean(Bar.class);
        bean.bar();
    }


    protected static class ClassPathBeanDefinitionScannerTest extends ClassPathBeanDefinitionScanner {

        public ClassPathBeanDefinitionScannerTest(BeanDefinitionRegistry registry) {
            super(registry);
        }

        @Override
        protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
            return super.doScan(basePackages);
        }
    }

}
