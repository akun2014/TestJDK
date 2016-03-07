package com.gk.common.entity;

public class Son2 extends Son1 {
	private static final long serialVersionUID = 6807541344564170677L;

	String son2 ;
	public Son2(String name, String myname,String son2,int num) {
		super(name,myname, num);
		this.son2 = son2;
		System.out.println("son2 init...");
	}
	public String getMyName() {
		return son2;
	}
	public void setMyName(String myName) {
		this.son2 = myName;
	}
	@Override
	public String toString() {
		return super.toString()+"Son1[myName="+son2+"]";
	}

	 private void writeObject(java.io.ObjectOutputStream s)
		        throws java.io.IOException{
		 s.defaultWriteObject();
		 s.writeUTF(this.getName());
	 }

}
