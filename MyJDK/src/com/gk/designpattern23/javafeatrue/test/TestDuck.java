package com.gk.designpattern23.javafeatrue.test;

public class TestDuck {

	public static void main(String[] args) {

		Duck duck = new ReadHeadDuck();
		duck.performFly();
		duck.performQuack();
		//动态改变飞的模式
		duck.setFb(new FlyNoWay());
		duck.performFly();
		//duck.d
	}

}
