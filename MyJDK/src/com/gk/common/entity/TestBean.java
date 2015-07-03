package com.gk.common.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 用户自己定义对象
 * 
 * @author gk
 * 
 */
public class TestBean implements Serializable, Comparable<TestBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int num;

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
	public int compareTo(TestBean o) {
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
		if (obj.getClass() != TestBean.class)
			return false;
		TestBean testBean = (TestBean) obj;
		return testBean.getName().equals(name);
	}

	@Override
	public String toString() {
		return "TestBean:" + name;
	}

}
