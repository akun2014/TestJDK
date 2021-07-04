package com.ownerkaka.testjdk.spring.aop;

import com.ownerkaka.testjdk.support.bean.Bar;
import com.ownerkaka.testjdk.support.service.BarService;
import com.ownerkaka.testjdk.support.service.impl.BarServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.DecoratingProxy;

import static org.junit.Assert.*;

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
        //bar will use cglib create proxy
        Bar bar = applicationContext.getBean(Bar.class);
        assertEquals("around:baraoptest", bar.append("aoptest"));
        assertTrue(bar instanceof Bar);

        //bar will use jdk Proxy create proxy
        BarService barService = applicationContext.getBean(BarService.class);
        assertFalse(barService instanceof BarServiceImpl);
//        barService.bar("aoptest");
        barService.bar();

        assertTrue(SpringProxy.class.isAssignableFrom(barService.getClass()));
        assertTrue(Advised.class.isAssignableFrom(barService.getClass()));
        assertTrue(DecoratingProxy.class.isAssignableFrom(barService.getClass()));
    }

    @Test
    public void testLookUpMethod() {
        Bar bar = applicationContext.getBean("bar", Bar.class);
        Assert.assertNotNull(bar.getHuman());
        Assert.assertNotSame(bar.getHuman(), bar.getHuman());
    }

    @Test
    public void circularRef() {
        Bar bar = applicationContext.getBean(Bar.class);
        assertEquals("around:baraoptest", bar.append("aoptest"));
        assertTrue(bar instanceof Bar);
    }
}
