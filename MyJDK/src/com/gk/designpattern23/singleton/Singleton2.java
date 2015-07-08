package com.gk.designpattern23.singleton;

/**
 * 使用volatile关键字确保singleton变量被初始化成Singleton2实例时，
 *  多个线程可以正确处理singleton变量
 */
public class Singleton2 {
	private volatile static Singleton2 singleton;

	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		synchronized (Singleton2.class) {
			if (singleton == null) {
				singleton = new Singleton2();
			}
		}
		return singleton;
	}
}
