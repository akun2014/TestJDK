package com.gk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by akun on 2019/3/12.
 */
@Slf4j
public class RemoveDuplicates_26 {
    @Test
    public void test() throws Exception {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3};
        int result = removeDuplicates(nums);
        for (int i = 0; i < result; i++) {
            System.out.print(nums[i]);
        }
        System.out.println();
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 双指针法 快慢指针
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            System.out.println("j:" + j + " " + "i:" + i + " " + Arrays.toString(nums));
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
