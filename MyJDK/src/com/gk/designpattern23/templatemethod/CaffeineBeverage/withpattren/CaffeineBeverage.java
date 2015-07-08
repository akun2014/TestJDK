package com.gk.designpattern23.templatemethod.CaffeineBeverage.withpattren;

/**
 * 这个抽象类封装了煮咖啡或者茶的实现步骤
 * 
 */
public abstract class CaffeineBeverage {

	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}

	//下面定义的两个抽象方法有子类具体实现
	abstract void brew();

	abstract void addCondiments();

	void boilWater() {
		System.out.println("开始煮水");
	}

	void pourInCup() {
		System.out.println("把饮料放入杯中");
	}
}
