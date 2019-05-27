package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.factory.generoal.Sender;
import com.ownerkaka.testjdk.designpattern23.proxy.pattren.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		Sender sender = new Proxy();
		sender.sender();
	}
}
