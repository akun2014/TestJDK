package com.gk.designpattern23.factory.abstractor;

import com.gk.designpattern23.factory.generoal.MailSender;
import com.gk.designpattern23.factory.generoal.Sender;

public class SenderMailFactory implements Provider {

	@Override
	public Sender produce() {
		return new MailSender();
	}

}
