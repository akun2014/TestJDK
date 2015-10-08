package com.gk.common.entity;

public class Son1 extends Parents {
	private static final long serialVersionUID = 6807541344564170677L;

	
	String myName ;
	public Son1(String name, String myname,int num) {
		super(name, num);
		this.myName = myname;
		System.out.println("son1 init...");
	}
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	@Override
	public String toString() {
		return super.toString()+"Son1[myName="+myName+"]";
	}

}
