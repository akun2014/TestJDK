package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by akun on 2019/3/13.
 */
@Slf4j
public class RemoveElement_27 {
    @Test
    public void test() throws Exception {
        int[] nums = new int[]{3, 2, 2, 3};
        int val = 3;
        Assert.assertEquals(2, removeElement(nums, val));
    }


    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
