package com.ownerkaka.testjdk.designpattern23.javafeatrue.test;

public abstract class Duck {
	protected FlyBehavior fb;
	protected QuackBehavior qb;

	public void performQuack(){
		qb.quack();
	}
	public void performFly(){
		fb.fly();
	}
	public void setFb(FlyBehavior fb) {
		this.fb = fb;
	}
	public void setQb(QuackBehavior qb) {
		this.qb = qb;
	}
		
	
}
