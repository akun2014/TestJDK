package com.ownerkaka.testjdk.designpattern23.observer.myimpl;

public class BuGaoBan2 implements BuGaoBan {

	@Override
	public void update(int a, int b, int c) {
		System.out.println("我是第二块布告板" + a + "" + b + "" + c);

	}

	@Override
	public void follow() {

		MyObserver.register(this);
	}

	@Override
	public void unfollow() {
		MyObserver.unregister(this);
		// TODO Auto-generated method stub

	}
}
