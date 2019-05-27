package com.ownerkaka.testjdk.collection.queue;

import java.util.LinkedList;
import java.util.Queue;

import com.ownerkaka.testjdk.common.entity.TestBean;
import junit.framework.TestCase;

import org.junit.Test;

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
		 * add()/offer()方法都可以使元素进站
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
			/**
			 * element()与peek()方法的不同之处在于，
			 * element()方法在队列为空时返回NoSuchElementException 
			 */
			// Retrieves and removes the head of this queue,
			// or returns null if this queue is empty.

			/**
			 * 使用poll方法后，队列的大小将会减小，即为队列中实际存在的元素个数
			 */
			 queue.poll();
			 /**
			  * remove()/poll()方法的不同之处在于remove()在队列为空时，
			  * 将抛出NoSuchElementException
			  */
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
		
		TestBean bean0 = new TestBean();
		bean0.setName("bean1");
		
		queue2.add(bean1);
		queue2.add(bean2);
		queue2.add(bean3);
		
		/**
		 * contains()方法在底层将调用TestBean覆写的equal方法来比较
		 * 
		 */
		
		//调用TestBean覆写的hashCode()方法，Object类的hashCode方法，返回的是对象的地址
		assertEquals(bean0.hashCode(), bean1.hashCode());
		assertEquals(bean0, bean1);//在底层调用的是TeatBean覆写的equal方法进行比较
		assertTrue(bean0.equals(bean1));
		assertTrue("测试包含", queue2.contains(bean0));
		
	}
	
	@Test
	public void test3(){
		
		queue.clear();//清空队列
		//queue.retainAll(Collection o);//求两个队列的交集
		//queue.remove(Object o) ,删除队列中指定的元素，该方法在Collection接口中定义
	}
	
}
