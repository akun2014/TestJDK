package com.gk.designpattern23.proxy.pattren;

import com.gk.designpattern23.factory.generoal.MailSender;
import com.gk.designpattern23.factory.generoal.Sender;

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
