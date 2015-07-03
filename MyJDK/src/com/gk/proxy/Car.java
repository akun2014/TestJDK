package com.gk.proxy;


public class Car implements Moveable {
	private String name;
	private double value;
	public double size;
	public Car (){}
	public Car (String name,double value){
		this.name = name;
		this.value = value;
	}
	public void move() throws Exception{
		Thread.sleep(1000);
		System.out.println("汽车行驶中");
	}

}
