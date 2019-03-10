package com.gk.proxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/*
 *JDK实现动态代理 
 */
@Slf4j
public class ProxyTest {


    @Test
    public void proxyTest() throws Exception {

        Car car = new Car();
        InvocationHandler h = new TimeHandler(car);
        Class<?> cls = car.getClass();
        log.info("{}", cls.toGenericString());
        Field[] fields = cls.getFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            log.info("field {} type name:{}", field.getName(), field.getType().getTypeName());
        }

        Moveable m = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
        m.move();
    }


    @Test
    public void proxyCodeGenerator() throws Exception {
        Car car = new Car();
        String simpleName = car.getClass().getSimpleName() + "Proxy";
        byte[] proxyClass = ProxyGenerator.generateProxyClass(simpleName, car.getClass().getInterfaces());

        FileUtils.writeByteArrayToFile(new File(simpleName + ".class"), proxyClass);
    }

    @Test
    public void proxyTest2() throws Exception {
        Class<?> proxyClass = Proxy.getProxyClass(getClass().getClassLoader(), Car.class.getInterfaces());
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        InvocationHandler timeHandler = new TimeHandler(new Car());
        Moveable mo = (Moveable) constructor.newInstance(timeHandler);
        mo.move();
    }
}
