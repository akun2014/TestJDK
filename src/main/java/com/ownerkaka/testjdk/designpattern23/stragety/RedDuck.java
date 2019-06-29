package com.ownerkaka.testjdk.designpattern23.stragety;

public class RedDuck extends Duck {

	@Override
	public void display() {

		System.out.println("我是红头鸭");
	}

	public static void main(String[] args) {

		Duck duck = new RedDuck();
		FlyingBehavior fly = new FlyWithWin();
		QuackBehaveior quack = new Quack();
		duck.setFlying(fly);
		duck.setQuack(quack);
		
		duck.display();
		duck.fly();
		duck.quack();
		duck.swim();
	}

}
