package com.ownerkaka.testjdk.jvm.classloader;

import java.lang.reflect.Method;

public class TestClassIdentity {
	public static void main(String[] args) {
		new TestClassIdentity().test();
	}

	public void test() { 
	   
	    String className = "com.test.Test"; 	
	    try { 
	        Class<?> class1 = Class.forName(className,true,FileSystemClassLoader.getSystemClassLoader()); 
	        Object obj1 = class1.newInstance(); 
	        Class<?> class2 = Class.forName(className,true,getClass().getClassLoader()); 
	        Object obj2 = class2.newInstance(); 
	        Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class); 
	        setSampleMethod.invoke(obj1, obj2); 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	 }
}
