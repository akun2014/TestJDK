package com.gk.queue;

import java.util.LinkedList;
import java.util.Queue;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 普通队列的特性:FIFO
 * 即先进后出
 * @author gk
 *
 */
public class TestQueue extends TestCase{

	Queue<String> queue = new LinkedList<String>(); ;
	@Before
	public void beforeTest(){
		System.out.println("测试去队列的特新");
	}
	@Test
	public void test1(){
		queue.add("b");
		queue.add("a");
		queue.add("c");
		
		System.out.println(queue.toString());
	}
	@After
	public void after(){
		System.out.println("测试结束");
	}
}
