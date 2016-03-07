package com.gk.designpattern23.stragety.pattren;

public abstract class Duck {

	private FlyingBehavior flying;
	private QuackBehaveior quack;
	public abstract void display();
	public void swim(){
		System.out.println("所有的鸭子都会游泳");
	}
	public void setFlying(FlyingBehavior flying) {
		this.flying = flying;
	}
	public void setQuack(QuackBehaveior quack) {
		this.quack = quack;
	}
	public void fly(){
		flying.fly();
	}
	public void quack(){
		quack.quack();
	}
	
}
