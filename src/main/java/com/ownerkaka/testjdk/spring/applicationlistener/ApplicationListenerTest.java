package com.ownerkaka.testjdk.spring.applicationlistener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author akun
 * @since 2019-05-12
 */
@Slf4j
@Configuration
public class ApplicationListenerTest {

    private static class Foo1 implements ApplicationListener<RequestHandledEvent> {
        @Override
        public void onApplicationEvent(RequestHandledEvent event) {
            String description = event.getDescription();
        }
    }

    private static class Foo2 implements ApplicationListener<ServletRequestHandledEvent> {
        @Override
        public void onApplicationEvent(ServletRequestHandledEvent event) {
            String clientAddress = event.getClientAddress();
        }
    }

    private static class Foo3 implements ApplicationListener<ContextStartedEvent> {
        @Override
        public void onApplicationEvent(ContextStartedEvent event) {
            ApplicationContext applicationContext = event.getApplicationContext();
        }
    }

    @EventListener(classes = {ApplicationReadyEvent.class})
    public void onLoadUp(ApplicationReadyEvent event) {
        ConfigurableListableBeanFactory beanFactory = event.getApplicationContext().getBeanFactory();
        Iterator<String> iterator = beanFactory.getBeanNamesIterator();
        Map<String, String> beanData = new HashMap<>(16);
        while (iterator.hasNext()) {
            String next = iterator.next();
            Object bean = beanFactory.getBean(next);
            beanData.put(next, bean.getClass().getCanonicalName());
        }
        log.info("bean:{}", JSON.toJSONString(beanData, true));
    }

}