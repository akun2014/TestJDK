package com.gk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author akun
 * @date 2019-03-31
 */
@Slf4j
public class MaximumSubarray_53 {

    public int maxSubArray(int[] nums) {

        return 9;
    }

    @Test
    public void test() throws Exception {
//        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = new int[]{-2, 1, -3, -4, -1, -2, 1, -5, -1};
        log.info("{}", maxSubArray2(nums));
    }

    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
