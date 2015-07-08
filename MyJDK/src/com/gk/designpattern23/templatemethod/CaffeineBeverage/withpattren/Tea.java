package com.gk.designpattern23.templatemethod.CaffeineBeverage.withpattren;

public class Tea extends CaffeineBeverage {

	@Override
	void brew() {

		System.out.println("用沸水煮茶");
	}

	@Override
	void addCondiments() {

		System.out.println("加柠檬");
	}

}
