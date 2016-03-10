package com.gk.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {

	public static void main(String[] args) {

		String name = "java.util.Date";
		
		try {
			Class<?> cl = Class.forName(name);
			Class<?> superClazz = cl.getSuperclass();
			String modifiers = Modifier.toString(cl.getModifiers());
			if(modifiers.length()>0) System.out.print(modifiers+"");
			
			System.out.print("class "+ name);

		    if(superClazz!=null&& superClazz!=Object.class)  
		    	      System.out.print("extends" +superClazz.getName());
		    System.out.println("\n{\n");
		     printConstructor(cl);
		     System.out.println();
		     printMethods(cl);
		     System.out.println();
		     printFields(cl);
		      System.out.println("}");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	public static void printConstructor(Class<?> cl){
		Constructor<?>[] con = cl.getDeclaredConstructors();
		
		for (Constructor<?> c : con) {
			String name = c.getName();
			System.out.print("  ");
			String modifiers = Modifier.toString(c.getModifiers());
			if(modifiers.length()>0) System.out.print(modifiers+" ");
			System.out.print(name+"(");
			
			Class<?>[] paramTYpe = c.getParameterTypes();
			for (int i = 0; i < paramTYpe.length; i++) {
				if(i>0) System.out.print(", ");
				 System.out.print(paramTYpe[i].getName());
			}
			  System.out.println(");");
		}
	}
	public static void printMethods(Class<?> cl){
		Method[] methods = cl.getDeclaredMethods();
		
		for (Method method : methods) {
			Class<?> retType = method.getReturnType();
			String name = method.getName();
			
			System.out.print(" ");
			
			String modifiers = Modifier.toString(method.getModifiers());
			if(modifiers.length()>0) System.out.print(modifiers+" ");
			System.out.print(retType.getName()+" "+name+"(");
			
			Class<?>[] paramType = method.getParameterTypes();
			for(int j=0;j<paramType.length;j++){
				if(j>0) System.out.print(",");
				  System.out.print(paramType[j].getName());
			}
			System.out.println(");");
		}
	}
	public static void printFields(Class<?> cl){
		Field[] fields = cl.getDeclaredFields();
		
		for (Field field : fields) {
			Class<?> type = field.getType();
			String name = field.getName();
			System.out.print("  ");
			String modifiers = Modifier.toString(field.getModifiers());
			if(modifiers.length()>0) System.out.print(modifiers + " ");
			System.out.println(type.getName()+" "+name+";");
		}
	}

}
