package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.singleton.Singleton;
import com.gk.designpattern23.singleton.Singleton1;

public class TestSingleton {

	public static void main(String[] args) {

		 Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		//System.out.println(singleton1.equals(singleton2));
	}

}
