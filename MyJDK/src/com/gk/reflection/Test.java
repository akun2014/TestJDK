package com.gk.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {

		Class<Obj> clazz = Obj.class;
		Field[] fields = clazz.getFields();
		System.out.println(fields[0].toString());
		
		Method[] methods = clazz.getMethods();
		System.out.println(methods[0].toString());
		
		Constructor<?>[] con =clazz.getConstructors();
		System.out.println(con[0]);
	}
	

}
