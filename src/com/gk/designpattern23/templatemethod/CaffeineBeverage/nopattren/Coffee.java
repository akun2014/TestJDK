package com.gk.designpattern23.templatemethod.CaffeineBeverage.nopattren;

public class Coffee  {

	public void boilWater(){
		System.out.println("开始煮水");
	}
	void brew() {

		System.out.println("用沸水煮咖啡");
	}

	public void pourInCup(){
		System.out.println("把咖啡倒入杯中");
	}
	void addCondiments() {

		System.out.println("加牛奶");
	}

}
