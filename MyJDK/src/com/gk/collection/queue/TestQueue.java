package com.gk.collection.queue;

import java.util.LinkedList;
import java.util.Queue;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gk.common.entity.TestBean;

/**
 * 普通队列的特性:FIFO 即先进后出 队列由LinkedList提供唯一实现
 * 
 * @author gk
 * 
 */
public class TestQueue extends TestCase {

	// 队列中的元素为系统类
	Queue<String> queue = new LinkedList<String>();
	Queue<TestBean> queue2 = new LinkedList<TestBean>();


	/**
	 * 测试不同元素的进站
	 */
	@Test
	public void test1() {
		/**
		 * add/affer方法都可以使元素进站
		 * 相同点:在进站成功后，将返回true
		 * 不同点:在违背容量限制时，add方法将抛出IllegalStateException异常
		 * 而offer方法将返回false
		 */
		// 进站
		queue.add("b");
		queue.add("a");
		queue.add("c");
		queue.add("d");

		boolean falg = queue.offer("offer method may be good");
		assertTrue(falg);
		assertEquals("queue size",5, queue.size());
		// 出站
		for (int i = 0; i < queue.size(); i++) {
			/**
			 * Retrieves, but does not remove, the head of this queue, or
			 * returns null if this queue is empty
			 */
			System.out.println(queue.peek());
			// Retrieves and removes the head of this queue,
			// or returns null if this queue is empty.

			/**
			 * 使用poll方法后，队列的大小将会减小，即为队列中实际存在的元素个数
			 */
			 queue.poll();
		}

		 System.out.println(queue.toString());
	}

	@Test
	public void testUserBean(){
		TestBean bean1= new TestBean();
		bean1.setName("bean1");
		TestBean bean2= new TestBean();
		bean2.setName("bean2");
		TestBean bean3= new TestBean();
		bean3.setName("bean3");
		
		queue2.add(bean1);
		queue2.add(bean2);
		queue2.add(bean3);
		
		System.out.println(queue2.peek());
	}
}
