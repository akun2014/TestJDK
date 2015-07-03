package com.gk.algorithm.base;


/**
 * 冒泡排序算法
 * @author gk
 *
 */
public class BubbleSort {

	public static void bubbleSort(int[] a){
		int i,j,temp,flag=1;
		int n = a.length;
		
		for(i=n-1;i>1&&flag==1;i--){
			flag=0;
			for(j=0;j<i;j++){
				if(a[j]>a[j+1]){
					flag = 1;
					temp = a[j];
					a[j] =a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
}
