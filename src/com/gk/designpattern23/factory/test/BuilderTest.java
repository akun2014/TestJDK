package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.budiler.pattren.Builder;


public class BuilderTest {

	public static void main(String[] args) {
		Builder builder = new Builder();
		builder.produceMailSender(3);
	}
}
