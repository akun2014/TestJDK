package com.ownerkaka.testjdk.designpattern23.observer.myimpl;

import java.util.HashSet;
import java.util.Set;

public class MyObserver {

	static Set<BuGaoBan> bugaobans = new HashSet<BuGaoBan>();
	
	public static void register(BuGaoBan bugaoban){
		bugaobans.add(bugaoban);
	}
	public static void unregister(BuGaoBan bugaoban){
		bugaobans.remove(bugaoban);
	}
	
	public static void update(int a,int b,int c){
		for (BuGaoBan bugaoban : bugaobans) {
			bugaoban.update(a, b, c);
		}
	}
}
