package com.ownerkaka.testjdk.spring.aop;

import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.Assert;

/**
 * Created by akun on 2019/3/4.
 */
@Slf4j
public class AopTest {

    private GenericApplicationContext applicationContext;

    @Before
    public void init() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");
        applicationContext = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("application-aop.xml");
        applicationContext.refresh();
    }

    @Test
    public void test() {
        Bar bar = applicationContext.getBean(Bar.class);
        String aoptest = bar.append("aoptest");
        log.info("{}", aoptest);
    }

    @Test
    public void testLookUpMethod() {
        Bar bar = applicationContext.getBean("bar", Bar.class);
        Assert.notNull(bar.getHuman(), "human not null");
    }
}
