package com.ownerkaka.testjdk.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

import org.junit.Test;

public class TestArrayList extends TestCase{

	@Test
	public void test(){

		List<String> list = new ArrayList<>();
		
		list.add("a");
		list.add("d");
		list.add(0, "c");
		
		list.remove(0);
		list.remove("a");//该方法定义在Collection接口中
		System.out.println(list);
	}
	
	@Test
	public void test2(){
		List<String> list = Arrays.asList("b c d e f a".split(" "));
		//index(Object o): or -1 if there is no such index
		assertEquals(0, list.indexOf(list.get(0)));
	}
	@Test
	public void test3(){
		List<String> list = Arrays.asList("b c d e f a".split(" "));
		
		ListIterator<String> it = list.listIterator();
		while(it.hasPrevious()){
			System.out.print(it.previous()+" ");
		}
	}
}
