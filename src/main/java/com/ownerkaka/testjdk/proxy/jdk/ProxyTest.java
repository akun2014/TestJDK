package com.ownerkaka.testjdk.proxy.jdk;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author akun
 * JDK实现动态代理
 */
@Slf4j
public class ProxyTest {


    @Test
    public void proxyTest() throws Exception {
        Car car = new Car();
        InvocationHandler invocationHandler = new TimeHandler(car);

        Moveable m = (Moveable) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Moveable.class}, invocationHandler);
        Assert.assertTrue(Proxy.isProxyClass(m.getClass()));
        Assert.assertFalse(m instanceof Car);
        Assert.assertTrue(Moveable.class.isAssignableFrom(m.getClass()));
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
