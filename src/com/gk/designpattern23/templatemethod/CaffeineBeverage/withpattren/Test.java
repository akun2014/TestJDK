package com.gk.designpattern23.templatemethod.CaffeineBeverage.withpattren;

import junit.framework.TestCase;

public class Test extends TestCase{

	public void testTea(){
		CaffeineBeverage caffeineBeverage = new Tea();
		caffeineBeverage.prepareRecipe();
	}
	public void testCoffee(){
		CaffeineBeverage caffeineBeverage = new Coffee();
		caffeineBeverage.prepareRecipe();
		
	}
}
