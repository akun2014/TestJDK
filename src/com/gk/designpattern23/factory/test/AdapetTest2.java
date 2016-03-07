package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.adapter.pattren.SourceSub;
import com.gk.designpattern23.adapter.pattren.Sourceable;

public class AdapetTest2 {

	public static void main(String[] args) {
		Sourceable source = new SourceSub();
		source.method1();
		//source.method2();
	}
}
