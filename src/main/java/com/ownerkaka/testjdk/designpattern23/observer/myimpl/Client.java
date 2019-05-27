package com.ownerkaka.testjdk.designpattern23.observer.myimpl;


import org.junit.Before;
import org.junit.Test;



public class Client{

	@Before
	public void test(){
		
		BuGaoBan bugaoban1 = new BuGaoBan1();
		BuGaoBan bugaoban2 = new BuGaoBan2();
		
		 
		bugaoban1.follow();
		bugaoban2.follow();
		
		bugaoban1.unfollow();
		
	}
	@Test
	public void test2(){
		WeatherData w = new WeatherData();
		w.meansurementsChange();
	}
	
}
