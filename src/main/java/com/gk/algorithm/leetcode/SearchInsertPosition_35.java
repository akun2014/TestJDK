package com.gk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author akun
 * @date 2019-03-31
 */
@Slf4j
public class SearchInsertPosition_35 {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middleIndex = (left + right) / 2;
        if (target < nums[middleIndex]) {
            right = middleIndex;
        } else {
            left = middleIndex;
        }
        log.info("left:{} right:{}", left, right);
        for (int i = left; i < right + 1; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    @Test
    public void test() throws Exception {
//        int[] nums = new int[]{1, 3, 5, 6};
//        int target = 7;

        int[] nums = new int[]{1};
        int target = 0;
        Assert.assertEquals(0, searchInsert(nums, target));
    }
}
