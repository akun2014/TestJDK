package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.adapter.ClazzAdapter;

public class AdapterTest {

	public static void main(String[] args) {

		ClazzAdapter adapter = new ClazzAdapter();
		adapter.method1();
		adapter.method2();
	}

}
