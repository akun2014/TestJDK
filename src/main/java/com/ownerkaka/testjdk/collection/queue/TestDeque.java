package com.ownerkaka.testjdk.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 双端队列
 * @author gk
 *
 */
public class TestDeque extends TestCase {

	/**
	 * 由ArrayDeque实现
	 */
	@Test
	public void test1(){
		Deque<String> deque = new ArrayDeque<>();
		//进站,两者的区别在于，后者在添加进站失败会抛出异常
		deque.add("a");
		deque.addFirst("b");
		deque.addLast("c");
		deque.offer("d");
		deque.offerFirst("e");
		deque.offerLast("f");
		
		//出站
		String element = deque.element();
		String removeFirst = deque.removeFirst();
		deque.removeLast();
		deque.removeFirstOccurrence("a");
		deque.removeLastOccurrence("b");
		
		System.out.println(element+removeFirst);
		System.out.println(deque);
		//output
		//[b, a, c]
				
	}
	//add/offer方法的区别
	//在有限容量队列中，add方法在添加失败后会抛出IllegalStateException异常，而offer会返回false
	@Test
	public void test1_1(){
		Deque<String> deque = new LinkedBlockingDeque<>(3);
		
		deque.add("a");
		deque.add("b");
		deque.add("c");
		//deque.add("容量超出限制，将抛出IllegalStateException");//
		
		boolean flag = deque.offer("容量超出限制，将返回false");
		assertFalse(flag);
		
	}
	/**
	 * 由LinkedList实现
	 */
	@Test 
	public void test2(){
		Deque<String> deque = new LinkedList<>();
		//进站
		deque.add("");
		deque.offer("");
		deque.offerFirst("");
		deque.addFirst("");
		deque.addLast("");
		deque.offerLast("");
		
		//出站，下面3个方法相同
		deque.pop();
		deque.poll();
		deque.pollFirst();
		
	}
}
