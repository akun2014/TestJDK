package com.gk.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author gk
 * @date 2015年10月19日
 * @time 上午9:17:33
 */
public class TestException {

	@Test
	public void test1() {
		File file = new File("g:test.txt");

		try {
			FileInputStream inputStream = new FileInputStream(file);

			System.out.println("该语句在上面语句出现异常时不会被执行");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("程序出现异常 。。。");
		}

	}

	@Test
	public void test2() {
		File file = new File("");

		try {
			FileInputStream inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("程序出现异常 ~~~");
		} finally {
			System.out.println("这里是 finally块 ");
		}
	}

	@Test
	public void test3() {

		int num = testReturn();
		
		System.out.println("testResult方法返回的参数num:"+num);
	}

	public int testReturn() {

		int num = 4;

		try {
			num /= 0;
			return num;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("程序出现错误了");
			return num;//程序在返回前会先执行finally块中的代码，然后才返回
		} finally {
			System.out.println(" 这里是 finally块");
			System.out.println("num:" + num);
		}
	}
	
	@Test
	public void testMyException(){
		try {
			myException();
		}catch (MyException e) {
			RuntimeException exception = new RuntimeException("运行时异常");   //异常转译
			exception.initCause(e);
			exception.printStackTrace();
		}catch(Exception e ){
			System.out.println("在这里处理例外的异常");
		}
	}
	
	public void myException () throws MyException{
		throw new MyException("自定义异常");
	}
}
