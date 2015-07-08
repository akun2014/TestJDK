package com.gk.designpattern23.observer;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class Client extends TestCase{

	MyObserver myObserver = new MyObserver();
	public void test(){
		BuGaoBan1 bugaoban1 = new BuGaoBan1();
		
		
		myObserver.register(bugaoban1);
		
		myObserver.update();
	}
}
