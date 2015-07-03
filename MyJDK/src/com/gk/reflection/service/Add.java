package com.gk.reflection.service;

public class Add implements CalculateI {

	@Override
	public void call(int a, int b) {

		System.out.println("a+b="+a+b);
	}

}
