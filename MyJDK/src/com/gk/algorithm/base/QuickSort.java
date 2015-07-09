package com.gk.algorithm.base;

/**
 * 快速排序算法
 * 
 * @author gk
 * 
 */
public class QuickSort {

	void quicksort(int[] a, int left, int right) {
		int i, j, t, temp;
		if (left > right)
			return;

		temp = a[left]; // temp中存的就是基准数
		i = left;
		j = right;
		while (i != j) {
			// 顺序很重要，要先从右边开始找
			while (a[j] >= temp && i < j)
				j--;
			// 再找左边的
			while (a[i] <= temp && i < j)
				i++;
			// 交换两个数在数组中的位置
			if (i < j) {
				t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
		// 最终将基准数归位
		a[left] = a[i];
		a[i] = temp;

		quicksort(a, left, i - 1);// 继续处理左边的，这里是一个递归的过程
		quicksort(a, i + 1, right);// 继续处理右边的 ，这里是一个递归的过程
	}

	public static void main(String[] args) {
		// int arr[] = new int[]{6 , 1 ,2 ,7 , 9 ,3 ,4 ,5 ,10,
		// 8,6,8,19};//定义全局变量，这两个变量需要在子函数中使用
		int arr[] = new int[] { 2, 8, 7, 1, 3, 5, 6, 4 };// 定义全局变量，这两个变量需要在子函数中使用
		new QuickSort().quicksort(arr, 0, arr.length - 1);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
