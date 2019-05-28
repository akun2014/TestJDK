package com.ownerkaka.testjdk.spring.ioc;

import com.ownerkaka.testjdk.spring.IOCBase;
import com.ownerkaka.testjdk.support.bean.Bar;
import com.ownerkaka.testjdk.support.bean.Person;
import com.ownerkaka.testjdk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by akun on 2019/2/28.
 */
@Slf4j
public class ComponentScanTest extends IOCBase {

    @Test
    public void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.ownerkaka.testjdk.support.bean", "com.ownerkaka.testjdk.spring.ioc");
        Bar bar = applicationContext.getBean(Bar.class);
        String beanName = Introspector.decapitalize(ClassUtils.getShortName(bar.getClass()));
        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition(beanName);

        assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);
        assertEquals(ConfigurableBeanFactory.SCOPE_SINGLETON, beanDefinition.getScope());
        assertNotNull(bar);
    }

    @Test
    public void test() {
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

        assertNotNull(bar);
        assertNotNull(user);
        assertNotNull(person);

        assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);
        assertTrue(userBeanDefinition instanceof GenericBeanDefinition);
        assertTrue(configurationTestBeanDefinition instanceof ScannedGenericBeanDefinition);
        assertEquals(personBeanDefinition.getClass().getSimpleName(), "ConfigurationClassBeanDefinition");
    }

    @Test
    public void testClassPathBeanDefinitionScanner() throws Exception {
        ClassPathBeanDefinitionScannerTest scanner = new ClassPathBeanDefinitionScannerTest(applicationContext);
        Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.doScan("com.ownerkaka.testjdk.support.bean");
        assertEquals(2, beanDefinitionHolders.size());
        // refresh beanFactory
        applicationContext.refresh();
        // after refreshed application can be used.
        Bar bar = applicationContext.getBean(Bar.class);
        assertNotNull(bar);
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
