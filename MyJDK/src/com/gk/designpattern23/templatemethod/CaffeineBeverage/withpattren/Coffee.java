package com.gk.designpattern23.templatemethod.CaffeineBeverage.withpattren;

public class Coffee extends CaffeineBeverage {

	@Override
	void brew() {

		System.out.println("用沸水煮咖啡");
	}

	@Override
	void addCondiments() {

		System.out.println("添加牛奶或者糖到咖啡中");
	}

}
