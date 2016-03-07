package com.gk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/*
 *JDK实现动态代理 
 */
public class Test {
	public static void main(String[] args) throws Exception {
		Car car = new Car();
		InvocationHandler h = new TimeHandler(car);
		Class<?> cls = car.getClass();
		Moveable m =(Moveable)Proxy.newProxyInstance(cls.getClassLoader(), 
				                                  cls.getInterfaces(), h);
		m.move();
		
	}

}
