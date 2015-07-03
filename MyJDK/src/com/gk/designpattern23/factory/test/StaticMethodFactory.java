package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.factory.generoal.Sender;
import com.gk.designpattern23.factory.generoal.StaticFactory;

public class StaticMethodFactory {

	public static void main(String[] args) {
		Sender sender = StaticFactory.produceMail();
		sender.sender();
	}
}
