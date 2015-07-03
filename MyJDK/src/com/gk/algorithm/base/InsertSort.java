package com.gk.algorithm.base;


/**
 * 插入排序算法
 * @author gk
 *
 */
public class InsertSort {
	/**
	 * 直接插入排序
	 * @param a
	 */
	public static void insertSort(int[] a){
	     int i,j,temp;
	     int n = a.length;
	     
	     for(i=0;i<n-1;i++){
	    	 temp = a[i+1];
	    	 for(j=i;j>-1&&temp<a[j];j--){
	    			 a[j+1] = a[j];
	    	 }
	    	 a[j+1] =temp;
	    	 
	     }
	}
	/**
	 * 希尔排序
	 */
	public static void shellSort(int[] a){
		int inner,outer;
		int n = a.length;
		
		int h =1;
		while(h<n/3)
			h = h*3+1;
		
		while(h>0){
			
		}
	}

}
