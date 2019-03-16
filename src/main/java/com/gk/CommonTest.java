package com.gk;

import com.gk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.Constants;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by akun on 2018/5/10.
 */
@Slf4j
public class CommonTest {
    String name = "gk";

    public static int test(int n) {
        return n;
    }

    public void test() {
    }

    private void privateMethod() {

    }

    protected void protectedMethod() {

    }

    public final void finalMethod() {

    }

    public static void main(String[] args) throws IntrospectionException {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
        String beanName = beanNameGenerator.generateBeanName(beanDefinition, new StaticApplicationContext());
        System.out.println(beanName);

        String shortName = ClassUtils.getShortName(User.class.getSimpleName());
        System.out.println(Introspector.decapitalize(shortName));
        Introspector.getBeanInfo(User.class);
    }

    @Test
    public void testt() {
        Constants constants = new Constants(XmlBeanDefinitionReader.class);
        constants.asNumber("123");
    }


}