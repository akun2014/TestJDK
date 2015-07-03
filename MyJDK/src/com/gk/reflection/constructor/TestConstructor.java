package com.gk.reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import com.gk.reflection.service.ConstructorObj;

public class TestConstructor {
	public static void main(String[] args) {
		Class<ConstructorObj> clazz = ConstructorObj.class;
		
		
		test3(clazz);
	}

	public static void test1(Class<ConstructorObj> clazz) {
		try {
			clazz.getConstructor(String.class).newInstance(new String("asd"));
			
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void test2(Class<ConstructorObj> clazz){
		Constructor<?>[] cons = clazz.getConstructors();
		Constructor<?> first = cons[1];
		try {
			Object obj = first.newInstance(new String("asd"));
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = first.getClass().toString();
		System.out.println(str);
	}
	public static void test3(Class<ConstructorObj> clazz){
		Constructor<?>[] cons = clazz.getDeclaredConstructors();
		Constructor<?> first = cons[1];
		first.setAccessible(true);
		for (Constructor<?> constructor : cons) {
			
			System.out.println(Modifier.toString(constructor.getModifiers()));
		}
		try {
			Object obj = first.newInstance(new Integer(1));
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cons.length);
	}
}
