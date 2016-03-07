package com.gk.designpattern23.factory.test;

import com.gk.designpattern23.templatemethod.pattren.AbstractCalculator;
import com.gk.designpattern23.templatemethod.pattren.Plus;

/**
 * 模板方法模式
 * @author gk
 *
 */
public class StrategyTest {

	 public static void main(String[] args) {  
	        String exp = "8+8";  
	        AbstractCalculator cal = new Plus();  
	        int result = cal.calculate(exp, "\\+");  
	        System.out.println(result);  
	    } 
}
