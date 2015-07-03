package com.gk.designpattern23.singleton;

public class Singleton1 {
	private static Singleton1 singleton;
	private Singleton1(){}
	
	public static synchronized Singleton1 getInstance(){
		if(singleton==null){
			singleton = new Singleton1();
		}
		return singleton;
	}
}

