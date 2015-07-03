package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.chain.responsibility.Client;

public class ChainResponsibilityTest {

	public static void main(String[] args) {
		
		
		for (int i = 1; i < 5; i++) {
			Client.handler(i);
		}
		
	}

}
