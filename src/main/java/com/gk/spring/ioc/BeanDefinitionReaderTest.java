package com.gk.spring.ioc;

import com.gk.support.bean.Bar;
import com.gk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.StaticApplicationContext;

/**
 * Created by akun on 2019/2/22.
 *
 * @see XmlBeanDefinitionReader
 * @see PropertiesBeanDefinitionReader
 */
@Slf4j
public class BeanDefinitionReaderTest {
    StaticApplicationContext applicationContext = new StaticApplicationContext();

    @Test
    public void testXmlBeanDefinitionReader() {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("application.xml");

        User user = (User) applicationContext.getBean("user");
        System.out.println(user.toString());
    }

    @Test
    public void testPropertiesBeanDefinitionReader() {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("application.properties");

        User user = (User) applicationContext.getBean("user");
        System.out.println(user.toString());
    }

    @Test
    public void testClassPathBeanDefinitionScanner() {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(applicationContext);
        scanner.scan("com.gk.support.bean");
        applicationContext.refresh();

        Bar bar = (Bar) applicationContext.getBean("bar");
        System.out.println(bar.toString());
    }
}
