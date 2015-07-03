package com.gk.designpattern23.templatemethod.pattren;

public class Plus extends AbstractCalculator{

	@Override
	public int calculate(int num1, int num2) {
		return num1+num2;
	}

}
