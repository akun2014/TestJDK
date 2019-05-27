/**
 * 
 */
package com.ownerkaka.testjdk.designpattern23.facade;

/**
 * @author gk
 * @date  2015年10月23日
 * @time  下午4:33:28
 * 
 * 门面类
 */
public class Facade {

	CookImpl cookImpl;
	WaiterImpl waiterImpl;
	PaymentImpl paymentImpl;
	
	
	public Facade(){
		this(new CookImpl(),new WaiterImpl(), new PaymentImpl());
	}
	
	public Facade(CookImpl cookImpl,WaiterImpl waiterImpl,PaymentImpl paymentImpl){
		this.cookImpl = cookImpl;
		this.waiterImpl = waiterImpl;
		this.paymentImpl = paymentImpl;
		
	}
	
	public void service(){
		String food = paymentImpl.pay(100.1);
		cookImpl.cook(food);
		waiterImpl.serve(food);
	}
}
