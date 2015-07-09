package com.gk.designpattern23.observer.myimpl;


public class WeatherData {

	public int getTamperature(){
		return 0;
	}
	public int  getHunidity(){
		return 0;
	}
	public int getPressure(){
		return 0;
	}
	
	public void meansurementsChange(){
		MyObserver.update(getTamperature(), getHunidity(), getPressure()); 
	}
}
