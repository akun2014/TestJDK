package com.gk.algorithm.google;

/**
 * 找第一个只出现一次的字符
 * @author gk
 *
 */
public class FirstAppear {

	public static void main(String[] args) {
		String str = "azzbaccdeff";
		int[] arr = new int[255];
		for(int i=0;i<str.length();i++){
				
			Character ch = new Character(str.charAt(i));
			int index = ch.hashCode();
			arr[index]++;
		}
		for(int i=0;i<str.length();i++){
			
			Character ch = new Character(str.charAt(i));
			int index = ch.hashCode();
			if(arr[index]==1){
				System.out.println(ch.charValue());
				break;
			}

		}
	}
}
