package com.gk.designpattern23.observer;

import java.util.HashSet;
import java.util.Set;

public class MyObserver {

	Set<BuGaoBan> bugaobans = new HashSet<>();
	
	public void register(BuGaoBan bugaoban){
		bugaobans.add(bugaoban);
	}
	
	public void update(){
		for (BuGaoBan bugaoban : bugaobans) {
			bugaoban.update(a, b, c);
		}
	}
}
