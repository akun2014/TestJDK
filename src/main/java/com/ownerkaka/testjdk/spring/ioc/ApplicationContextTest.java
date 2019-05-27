package com.ownerkaka.testjdk.spring.ioc;

import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by akun on 2019/3/16.
 */
@Slf4j
public class ApplicationContextTest {

    @Test
    public void testGenericXmlApplicationContext() throws Exception {
        GenericXmlApplicationContext applicationContext =
                new GenericXmlApplicationContext("application-test.xml");

        Bar bar = applicationContext.getBean(Bar.class);
        assertNotNull(bar);
        long startupDate = applicationContext.getStartupDate();
        log.info("{}", startupDate);
    }

    @Test
    public void testAnnotationConfigApplicationContext() throws Exception {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.gk.support");
        Bar bar = applicationContext.getBean(Bar.class);
        assertNotNull(bar);
    }

    @Test
    public void test() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("application-test.xml");

        GenericWebApplicationContext applicationContext =
                new GenericWebApplicationContext(beanFactory, null);
        applicationContext.refresh();

        Bar bar = applicationContext.getBean(Bar.class);
        assertNotNull(bar);
    }
}
