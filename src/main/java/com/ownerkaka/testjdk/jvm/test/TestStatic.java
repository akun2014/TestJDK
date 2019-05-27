package com.ownerkaka.testjdk.jvm.test;

public class TestStatic {

	static{
		System.out.println("静态代码块");
	}
	{
		System.out.println("代码块");
	}
}
