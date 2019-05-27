package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.singleton.Singleton;

public class TestSingleton {

	public static void main(String[] args) {

		 Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		//System.out.println(singleton1.equals(singleton2));
	}

}
