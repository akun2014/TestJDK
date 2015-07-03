package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.factory.generoal.Sender;
import com.gk.designpattern23.factory.generoal.SenderFactory;

public class GenerolFactory {

	public static void main(String[] args) {
		SenderFactory senderFactory = new SenderFactory();
		Sender sender = senderFactory.produce("mailsender");
		sender.sender();
	}
}
