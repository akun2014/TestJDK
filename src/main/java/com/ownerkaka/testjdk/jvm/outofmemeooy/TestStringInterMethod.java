package com.ownerkaka.testjdk.jvm.outofmemeooy;

public class TestStringInterMethod {

	public static void main(String[] args) {

		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.toString()==str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern().hashCode()+"/"+str2.hashCode());
		System.out.println(str2.intern()==str2);
	}

}
