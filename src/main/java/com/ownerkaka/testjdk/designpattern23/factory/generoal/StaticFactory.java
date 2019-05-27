package com.ownerkaka.testjdk.designpattern23.factory.generoal;

public class StaticFactory {

	public static Sender produceMail(){
		return new MailSender();
	}
	
	public static Sender produceSms(){
		return new SmsSender();
	}
}
