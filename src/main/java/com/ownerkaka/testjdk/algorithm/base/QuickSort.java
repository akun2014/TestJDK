package com.ownerkaka.testjdk.algorithm.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * 快速排序算法
 * 采用递归的方式，分而治之
 *
 * @author gk
 */
@Slf4j
public class QuickSort {

    private void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int i, j, temp;
            temp = a[left]; // temp中存的就是基准数
            i = left;
            j = right;
            while (i < j) {
                // 顺序很重要，要先从右边开始找
                while (a[j] >= temp && i < j)
                    j--;
                // 再找左边的
                while (a[i] <= temp && i < j)
                    i++;
                // 交换两个数在数组中的位置
                if (i < j) {
                    SortUtil.swap(a, i, j);
                }
            }
            // 最终将基准数归位
            a[left] = a[i];
            a[i] = temp;

            log.info("left:{} right:{}", left, right);
            quickSort(a, left, i - 1);// 继续处理左边的，这里是一个递归的过程
            quickSort(a, i + 1, right);// 继续处理右边的 ，这里是一个递归的过程
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{5, 8, 7, 4, 3, 1, 2};
//        int[] arr = new int[]{2, 8, 7, 1, 3, 5, 6, 4, 4};
        quickSort(arr, 0, arr.length - 1);
//        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 4, 5, 6, 7, 8}, arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 7, 8}, arr);
    }
}
