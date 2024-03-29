package com.ownerkaka.testjdk;

import com.ownerkaka.testjdk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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
        LinkedList<User> list = new LinkedList<>();
        LinkedHashMap<String, User> map = new LinkedHashMap();

        list.addFirst(new User());
        list.add(new User());

        User user = list.get(0);


        addSingletonFactory("", (ObjectFactory<User>) () -> {
            User user1 = new User();
            user1.setAge(21);
            return user1;
        });
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {


    }

    @Test
    public void testThreadMXBean() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = bean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            log.info("threadId={},name={}", threadInfo.getThreadId(), threadInfo.getThreadName());
        }
    }


}