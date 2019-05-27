package com.ownerkaka.testjdk.spring.ioc;

import com.ownerkaka.testjdk.support.bean.Foo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.core.convert.TypeDescriptor;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * Created by akun on 2019/2/27.
 */
@Slf4j
public class BeanWrapperTest {

    @Test
    public void test() {
        Foo foo = new Foo();
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(foo);
        beanWrapper.setAutoGrowNestedPaths(true);
        beanWrapper.setConversionService(null);

        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            TypeDescriptor typeDescriptor = beanWrapper.getPropertyTypeDescriptor(propertyDescriptor.getName());
            if (typeDescriptor.getAnnotation(null) != null) {
            }
        }

        beanWrapper.setPropertyValue("type", "test");
//        beanWrapper.setPropertyValue("user", new User());
        beanWrapper.setPropertyValue("user.name", "gk");

        PropertyValue propertyValue = new PropertyValue("member", Arrays.asList("bar"));
        beanWrapper.setPropertyValue(propertyValue);

        Assert.assertEquals(Foo.class, beanWrapper.getWrappedClass());
        Assert.assertEquals(foo, beanWrapper.getWrappedInstance());
        log.info("{}", foo.toString());
    }
}
