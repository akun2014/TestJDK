package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.adapter.SourceSub;
import com.ownerkaka.testjdk.designpattern23.adapter.Sourceable;

public class AdapetTest2 {

	public static void main(String[] args) {
		Sourceable source = new SourceSub();
		source.method1();
		//source.method2();
	}
}
