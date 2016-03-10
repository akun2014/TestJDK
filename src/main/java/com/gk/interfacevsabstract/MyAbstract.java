package com.gk.interfacevsabstract;

import com.gk.genericity.SampleGenericity;

public abstract class MyAbstract extends SampleGenericity{

	static final int a = 0;
	int b = 0;

	public MyAbstract(){
		
	}
	abstract void save();

	public void get() {
		System.out.println("定义具体方法");
	}
	public static void main(String[] args) {
		MyAbstract m = new MyAbstract() {
			
			@Override
			void save() {

				System.out.println("asd");
			}
		};
	}
}
