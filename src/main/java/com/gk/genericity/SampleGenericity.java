package com.gk.genericity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class SampleGenericity {

	public void test2(){
		//从下面的例子可以看出泛型不是协变的
		List<Integer> list= new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();
		//number = list;
		
		Integer[] i = new Integer[3];
		Number[] n = i;
		
//		List<String>[] lsa = new List<String>[10]; // illegal
//		 Object[] oa = lsa;  // OK because List<String> is a subtype of Object
//		 List<Integer> li = new ArrayList<Integer>();
//		 li.add(new Integer(3));
//		 oa[0] = li;
//		 String s = lsa[0].get(0);
		
	}
	
	public void test3(){
		Collection<Integer> i = new HashSet<Integer>();
		i.add(1);
		Collection<String> obj = new HashSet<String>();
		obj.add("asb");
		i.removeAll(obj);
	}
	
	public <T extends List<SampleGenericity>> void test4(T t){
		
	}
	public void test5(){
//		test4(new HashSet<SampleGenericity>());
		test4(new ArrayList<SampleGenericity>());
	}
	public <T>void test6(T t){
		
	}
}
