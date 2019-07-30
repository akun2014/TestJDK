package com.ownerkaka.testjdk.spring.springboot;

import com.ownerkaka.testjdk.support.bean.Bar;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

/**
 * @author akun
 * @since 2019-07-30
 */
@Slf4j
public class AnnotationConfigServletWebServerApplicationContextTests {

    @Test
    public void test() {
        AnnotationConfigServletWebServerApplicationContext applicationContext
                = new AnnotationConfigServletWebServerApplicationContext();
        //内嵌tomcat
        applicationContext.registerBean(TomcatServletWebServerFactory.class);
        applicationContext.scan("com.ownerkaka.testjdk.support.bean");
        //must invoke refresh() method to init applicationContext
        applicationContext.refresh();

        Assert.assertNotNull(applicationContext.getBean(Bar.class));
    }
}