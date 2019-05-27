package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.factory.generoal.Sender;
import com.ownerkaka.testjdk.designpattern23.factory.generoal.SenderFactoryMethods;

/**
 * 多方法工厂模式测试
 * @author gk
 *
 */
public class MethodsFactory {

	public static void main(String[] args) {
		SenderFactoryMethods senderFatoryMethods = new SenderFactoryMethods();
		Sender sender = senderFatoryMethods.produceMail();
		sender.sender();
	}
}
