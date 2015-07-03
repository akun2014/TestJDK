package com.gk.reflection.service;

public class ConstructorObj {
	
	public ConstructorObj(){
		System.out.println("无参构造方法");
	}
    public ConstructorObj(String name){
    	System.out.println("有参构造方法"+name);
    }
    public ConstructorObj(String... str){
    	for (String string : str) {
			System.out.print(string+" ");
		}
    }
    private ConstructorObj(int i){
    	System.out.println(i);
    }
    protected ConstructorObj(float f){
    	System.out.println(f);
    }
}
