package com.gk.algorithm.base;

/**
 * 直接插入排序
 * 
 * @author gk
 * 
 */
public class SelectSort {
	public static void selectSort(int[] a) {
		int i, j, small = 0;
		int temp;
		int n = a.length;

		for (i = 0; i < n - 1; i++) {
			small = i;
			for (j = i; j < n - 1; j++) {
				if (a[small] > a[j + 1])
					small = j + 1;
			}

			if (small != i) {
				temp = a[small];
				// a[i] = a[small];
				for (j = small; j > i; j--)
					a[j] = a[j - 1];
				a[i] = temp;
			}
		}
	}

}
