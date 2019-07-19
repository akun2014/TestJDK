package com.ownerkaka.testjdk.spring.ioc.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author akun
 * @since 2019-07-19
 */
@Slf4j
public class OwnerkakaBeanDefinitionParser implements BeanDefinitionParser {


    private final Class<?> beanClass;

    public OwnerkakaBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(beanClass);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.setLazyInit(false)
                .setAbstract(false).getBeanDefinition();
        String name = element.getAttribute("name");

        parserContext.getRegistry().registerBeanDefinition(name, beanDefinition);
        return beanDefinition;
    }
}