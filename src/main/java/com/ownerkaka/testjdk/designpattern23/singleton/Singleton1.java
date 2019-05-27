package com.ownerkaka.testjdk.designpattern23.singleton;

/**
 * 使用synchronized关键字来同步getInstance()这个方法
 */
public class Singleton1 {
	private static Singleton1 singleton;

	private Singleton1() {
	}

	public static synchronized Singleton1 getInstance() {
		if (singleton == null) {
			singleton = new Singleton1();
		}
		return singleton;
	}
}
