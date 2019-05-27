package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.factory.abstractor.Provider;
import com.ownerkaka.testjdk.designpattern23.factory.abstractor.SenderMailFactory;
import com.ownerkaka.testjdk.designpattern23.factory.generoal.Sender;


/**
 * 抽象工厂方法模式
 * @author gk
 *
 */
public class AbstratFactory {

	public static void main(String[] args) {
		Provider provider = new SenderMailFactory();
		Sender sender = provider.produce();
		sender.sender();
	}
}
