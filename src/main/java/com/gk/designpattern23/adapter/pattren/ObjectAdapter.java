package com.gk.designpattern23.adapter.pattren;

public class ObjectAdapter {

	private Source source = null;
	
	public ObjectAdapter(Source source){
		super();
		this.source = source;
	}
	
	public void method2(){
		System.out.println("this is the targeter method");
	}
	
	public void method1(){
		source.method1();
	}
}
