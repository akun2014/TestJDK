package com.gk.designpattern23.budiler.pattren;

import java.util.ArrayList;
import java.util.List;

import com.gk.designpattern23.factory.generoal.MailSender;
import com.gk.designpattern23.factory.generoal.Sender;
import com.gk.designpattern23.factory.generoal.SmsSender;

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
