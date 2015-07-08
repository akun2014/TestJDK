package com.gk.designpattern23.singleton;

/**
 * 使用“急切”创建实例，而不用延迟来创建实例
 */
public class Singleton {
	private static Singleton singleton = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return singleton;
	}
}
