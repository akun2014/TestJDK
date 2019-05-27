package com.ownerkaka.testjdk.designpattern23.stragety.pattren;

public class FlyWithNoway implements FlyingBehavior {

	@Override
	public void fly() {

		System.out.println("我不会飞翔");
	}

}
