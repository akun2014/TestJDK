package com.gk.designpattern23.factory.abstractor;

import com.gk.designpattern23.factory.generoal.Sender;
import com.gk.designpattern23.factory.generoal.SmsSender;

public class SenderSmsFactory implements Provider {

	@Override
	public Sender produce() {
      return new SmsSender();
	}

}
