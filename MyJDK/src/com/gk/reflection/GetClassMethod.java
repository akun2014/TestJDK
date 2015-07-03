package com.gk.reflection;

public class GetClassMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//通过对象获取Class对象
	public static void method1(Object obj){
		Class<?> clazz = obj.getClass();
		System.out.println(clazz.getName());
	}

}
