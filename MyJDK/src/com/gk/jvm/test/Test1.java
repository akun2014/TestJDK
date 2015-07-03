package com.gk.jvm.test;

public class Test1 {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader().toString());
	}
	public  void display(){
		ClassLoader classLoader = getClass().getClassLoader();
		ClassLoader parentLoader =classLoader.getParent();
		System.out.println(classLoader.toString());
		System.out.println(parentLoader.toString());
		System.out.println(ClassLoader.getSystemClassLoader().toString());
		/**
		 * 对于输出
		 * sun.misc.Launcher$AppClassLoader@712801c5
           sun.misc.Launcher$ExtClassLoader@798c668c
           sun.misc.Launcher$AppClassLoader@70a0afab
		 */
	}
}
