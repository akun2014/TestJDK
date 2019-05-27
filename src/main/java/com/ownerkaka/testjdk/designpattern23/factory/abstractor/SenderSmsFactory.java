package com.ownerkaka.testjdk.designpattern23.factory.abstractor;

import com.ownerkaka.testjdk.designpattern23.factory.generoal.Sender;
import com.ownerkaka.testjdk.designpattern23.factory.generoal.SmsSender;

public class SenderSmsFactory implements Provider {

	@Override
	public Sender produce() {
      return new SmsSender();
	}

}
