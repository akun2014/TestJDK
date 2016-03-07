package com.gk.designpattern23.factory.generoal;

/**
 * 多方法工厂模式
 * @author gk
 *
 */
public class SenderFactoryMethods {

	public Sender produceMail(){
		return new MailSender();
	}
	
	public Sender produceSms(){
		return new SmsSender();
	}
}
