/**
 * 
 */
package com.gk.designpattern23.facade;

/**
 * @author gk
 * @date  2015年10月23日
 * @time  下午4:10:27
 */
public class PaymentImpl {
	 public String pay(double price) {
	        String food = "快餐";
	        System.out.println("您已付款：" + price + "元，购买的是：" + food);
	        return food;
	    }
}
