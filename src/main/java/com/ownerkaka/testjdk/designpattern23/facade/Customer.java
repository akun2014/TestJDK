/**
 * 
 */
package com.ownerkaka.testjdk.designpattern23.facade;

import org.junit.Test;

/**
 * @author gk
 * @date  2015年10月23日
 * @time  下午4:34:11
 * 
 * 
 * 客户
 * 
 * 
 */
public class Customer {

	@Test
	public void haveDinner(){
		
		Facade facade = new Facade();
		facade.service();
	}
}
