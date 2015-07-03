package com.gk.reflection.test;

import com.gk.reflection.service.CalculateI;

public class TestFan {

	public static void main(String[] args) throws Throwable, Exception, Exception {

		String name ="com.gk.service.Add";
		//String name ="com.gk.service.Add";
		test(name);
	}
	//通过反射机制，在运行时选择执行哪个类
	public static void test(String clazzName) throws Exception, Exception, Exception{
		int a = 1;
		int b = 2;
		CalculateI cal = (CalculateI)Class.forName(clazzName).newInstance();
		cal.call(a, b);
		
	}

}
