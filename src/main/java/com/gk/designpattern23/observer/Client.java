package com.gk.designpattern23.observer;

import org.junit.Test;

public class Client {

	@Test
	public void test() {
		WeatherData weatherData = new WeatherData();

		CurrentConditionDisplay CurrentCondition上Display = new CurrentConditionDisplay(
				weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
	}
}
