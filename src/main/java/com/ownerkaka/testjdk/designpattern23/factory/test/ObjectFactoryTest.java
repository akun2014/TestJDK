package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.adapter.pattren.ObjectAdapter;
import com.ownerkaka.testjdk.designpattern23.adapter.pattren.Source;

public class ObjectFactoryTest {

	public static void main(String[] args) {
		Source source = new Source();
         ObjectAdapter objectAdapet = new ObjectAdapter(source);
         objectAdapet.method1();
         objectAdapet.method2();
	}
}
