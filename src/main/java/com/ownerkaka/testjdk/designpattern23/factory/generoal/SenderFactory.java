package com.ownerkaka.testjdk.designpattern23.factory.generoal;

public class SenderFactory {
 
	public Sender produce(String type){
		if("mailsender".equalsIgnoreCase(type)){
			return new MailSender();
		}else if("smssender".equalsIgnoreCase(type)){
			return new SmsSender();
		}else{
			System.out.println("请输入正确的类型");
			return null;
		}
	}
}
