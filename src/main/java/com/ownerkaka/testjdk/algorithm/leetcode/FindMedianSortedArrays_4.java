package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by akun on 2019/3/9.
 */
@Slf4j
public class FindMedianSortedArrays_4 {


    @Test
    public void test() throws Exception {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2};
        double retuls = findMedianSortedArrays(nums1, nums2);
        Assert.assertEquals(2, retuls, 0.1);

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }

}
