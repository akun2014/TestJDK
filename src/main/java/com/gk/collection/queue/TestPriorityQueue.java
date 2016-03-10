package com.gk.collection.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Test;

import com.gk.common.entity.TestBean;

import junit.framework.TestCase;

/**
 * 优先级队列 每次出队的是队列中优先级最高的元素 如果不指定Comparator，元素默认按照自然顺序排序，数字按照大小顺序，字符串按照字典顺序
 * 对于用户自定义类，需要在队列初始化时指定Comparator
 * 
 * @author gk
 * 
 */
public class TestPriorityQueue extends TestCase {

	@Test
	public void test1() {
		PriorityQueue<String> priorityQueue = new PriorityQueue<String>();

		// priorityQueue.addAll(Arrays.asList("a b c d e f g h i".split(" ")));
		priorityQueue.offer("a");
		priorityQueue.offer("b");
		priorityQueue.offer("e");
		priorityQueue.offer("c");
		priorityQueue.offer("ab");
		priorityQueue.offer("f");
		// priorityQueue.offer("a");
		int l = priorityQueue.size();
		for (int i = 0; i < l; i++) {
			//System.out.print(priorityQueue.poll() + " ");
		}
		// String str = priorityQueue.peek();
		// String str2 = priorityQueue.poll();

		// assertEquals("a", str);
		// assertEquals("a", str2);
		
		
		 
		Object[] o = priorityQueue.toArray();
		
		for (int i = 0; i < o.length; i++) {
			
			System.out.print(o[i]+" ");
			
		}
		System.out.println();
		/**
		 * 如果想得到队列排序的结果，应该使用下列方式
		 */
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			System.out.print(o[i]+" ");
			
		}
	}

	@Test
	public void test2() {
		TestBean bean1 = new TestBean();
		bean1.setName("bean1");
		bean1.setNum(1);
		TestBean bean2 = new TestBean();
		bean2.setName("bean2");
		bean2.setNum(2);
		TestBean bean3 = new TestBean();
		bean3.setName("bean3");
		bean3.setNum(3);
 
		Comparator<TestBean> comparator = new Comparator<TestBean>() {

			public int compare(TestBean o1, TestBean o2) {

				return (o1.getNum() - o2.getNum());
			}

		};

		PriorityQueue<TestBean> priorityQueue = new PriorityQueue<TestBean>(11,
				comparator);
		priorityQueue.offer(bean1);
		priorityQueue.offer(bean3);
		priorityQueue.offer(bean2);

		int l = priorityQueue.size();
		for (int i = 0; i < l; i++) {
			System.out.print(priorityQueue.poll().getName() + " ");
		}
		// output bean1 bean2 bean3
	}
}
