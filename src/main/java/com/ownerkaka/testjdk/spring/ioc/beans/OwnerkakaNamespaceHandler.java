package com.ownerkaka.testjdk.spring.ioc.beans;

import com.ownerkaka.testjdk.support.bean.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author akun
 * @since 2019-07-19
 */
@Slf4j
public class OwnerkakaNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("foo", new OwnerkakaBeanDefinitionParser(Foo.class));
    }
}