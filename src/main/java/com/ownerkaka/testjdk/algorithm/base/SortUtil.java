package com.ownerkaka.testjdk.algorithm.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-08-22
 */
@Slf4j
public class SortUtil {

    public static void swap(int[] a, int i, int j) {
        int t;
        t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}