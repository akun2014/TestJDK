package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.factory.abstractor.Provider;
import com.gk.designpattern23.factory.abstractor.SenderMailFactory;
import com.gk.designpattern23.factory.generoal.Sender;


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
