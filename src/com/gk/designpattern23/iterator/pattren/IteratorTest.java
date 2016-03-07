package com.gk.designpattern23.iterator.pattren;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("guikun");
		Iterator<String> it = list.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
}
