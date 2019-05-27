package com.ownerkaka.testjdk.common.entity;

import java.io.Serializable;

/**
 * 用户自己定义对象
 * 
 * @author gk
 * 
 */
public class Parents implements Serializable, Comparable<Parents> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient String name;
	private int num;

	public Parents(String name, int num) {
		this.name = name;
		this.num = num;
		System.out.println("parents init...");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public int compareTo(Parents o) {
		return (this.getNum() - o.getNum());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != Parents.class)
			return false;
		Parents testBean = (Parents) obj;
		return testBean.getName().equals(name);
	}

	@Override
	public String toString() {
		return "TestBean[name=" + name + ",num=" + num + "]";
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
		s.writeUTF(name);
	}
	 private void readObject(java.io.ObjectInputStream s)
		        throws java.io.IOException, ClassNotFoundException {
		 s.defaultReadObject();
		 this.name = s.readUTF();
	 }

}
