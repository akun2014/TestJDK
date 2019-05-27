package com.ownerkaka.testjdk.designpattern23.factory.test;

import com.ownerkaka.testjdk.designpattern23.factory.generoal.Sender;
import com.ownerkaka.testjdk.designpattern23.factory.generoal.SenderFactory;

public class GenerolFactory {

	public static void main(String[] args) {
		SenderFactory senderFactory = new SenderFactory();
		Sender sender = senderFactory.produce("mailsender");
		sender.sender();
	}
}
