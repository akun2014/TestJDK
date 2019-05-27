package com.ownerkaka.testjdk.designpattern23.javafeatrue.test;

public class ReadHeadDuck extends Duck {

	public ReadHeadDuck(){
		fb = new FlyWothWings();
		qb = new Quack();
	}
	public void display(){
		System.out.println("i'm real ReadHeadDuck");
	}

}
