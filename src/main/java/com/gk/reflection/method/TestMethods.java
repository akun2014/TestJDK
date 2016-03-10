package com.gk.reflection.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.gk.reflection.service.TestMethod;

public class TestMethods {

	public static void main(String[] args) {

		Class<TestMethod> method = TestMethod.class;
		test2(method);
	}
	public static void test1(Class<TestMethod> method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method me = method.getMethod("test1", null);
		me.invoke(new TestMethod() , null);
	}
	public static void test2(Class<TestMethod> method){
		try {
			Method me = method.getMethod("test1",String.class);
			Class<?>[] type = me.getParameterTypes();
			System.out.println(type.length);
			
		} catch (NoSuchMethodException | SecurityException  | IllegalArgumentException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
