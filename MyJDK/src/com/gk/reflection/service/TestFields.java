package com.gk.reflection.service;

public class TestFields {
	private String name ="field";
	protected int num;
	public String sex;
	protected String value;
	public static final String STR ="reflection";
	public String getName() {
		return name;
	}
	public int getNum() {
		return num;
	}
	public String getSex() {
		return sex;
	}
	public String getValue() {
		return value;
	}
	public static String getStr() {
		return STR;
	}

}
