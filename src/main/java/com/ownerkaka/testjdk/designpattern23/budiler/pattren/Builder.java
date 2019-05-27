package com.ownerkaka.testjdk.designpattern23.budiler.pattren;

import java.util.ArrayList;
import java.util.List;

import com.ownerkaka.testjdk.designpattern23.factory.generoal.MailSender;
import com.ownerkaka.testjdk.designpattern23.factory.generoal.Sender;
import com.ownerkaka.testjdk.designpattern23.factory.generoal.SmsSender;

public class Builder {
	private List<Sender> list = new ArrayList<Sender>();

	public void produceMailSender(int num){
		for (int i = 0; i < num; i++) {
			list.add(new MailSender());
		}
	}
	public void produceSmsSender(int num){
		for (int i = 0; i < num; i++) {
			list.add(new SmsSender());
		}
	}
}
