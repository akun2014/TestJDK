package com.ownerkaka.testjdk.algorithm.base;


import org.junit.Assert;
import org.junit.Test;

/**
 * 冒泡排序算法
 *
 * @author gk
 */
public class BubbleSort {

    public static void bubbleSort(int[] a) {
        int i, j, flag = 1;

        for (i = a.length - 1; i > 1 && flag == 1; i--) {
            flag = 0;
            for (j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    flag = 1;
                    SortUtil.swap(a, j, j + 1);
                }
            }
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{5, 8, 7, 4, 3, 1, 2};
//        int[] arr = new int[]{2, 8, 7, 1, 3, 5, 6, 4, 4};
        bubbleSort(arr);
//        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 4, 5, 6, 7, 8}, arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 7, 8}, arr);
    }
}
