/**
 * 
 */
package com.gk.exception;

/**
 * @author gk
 * @date  2015年10月19日
 * @time  上午10:32:41
 */
public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1048674714263936533L;
	public MyException(){
		
	}
	public MyException(String msg){
		super(msg);
	}
}
