package com.ownerkaka.testjdk.designpattern23.proxy;

import com.ownerkaka.testjdk.designpattern23.factory.generoal.MailSender;
import com.ownerkaka.testjdk.designpattern23.factory.generoal.Sender;

/**
 *静态代理模式
 * @author gk
 *
 */
public class Proxy  implements Sender{

	private Sender sender;
	public Proxy(){
		super();
		sender = new MailSender() ;
	}
	@Override
	public void sender() {
		befor();
		sender.sender();
		after();
		
	}
	public void befor(){
		System.out.println("method befor");
	}
	
	public void after(){
		System.out.println("method after");
	}
}
