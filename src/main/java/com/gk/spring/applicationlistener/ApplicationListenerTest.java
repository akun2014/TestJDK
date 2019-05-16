package com.gk.spring.applicationlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @author akun
 * @since 2019-05-12
 */
@Slf4j
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

}