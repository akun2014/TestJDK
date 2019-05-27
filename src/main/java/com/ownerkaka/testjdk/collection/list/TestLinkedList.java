package com.ownerkaka.testjdk.collection.list;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author gk
 * LinkedList底层由链表实现，它的主要特性:向链表中插入或者删除元素效率很高
 */
public class TestLinkedList {

	@Test
	public void test1(){
		List<String> list = new LinkedList<>();
		list.add("a");
		
	}
}
