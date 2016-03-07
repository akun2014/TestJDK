package com.gk.designpattern23.templatemethod.CaffeineBeverage.nopattren;

public class Tea  {

	public void boilWater(){
		System.out.println("开始煮沸水");
	}
	void brew() {

		System.out.println("用沸水煮茶");
	}

	public void pourInCup(){
		System.out.println("把茶倒入杯中");
	}
	void addCondiments() {

		System.out.println("加柠檬");
	}

}
