package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akun on 2019/3/12.
 */
@Slf4j
public class TwoSum_1 {

    @Test
    public void test() throws Exception {
        int[] nums = new int[]{2, 2, 11, 15};
        int target = 4;
        int[] result = twoSum2(nums, target);
        log.info("{}", Arrays.toString(result));
    }

    /**
     * 时间复杂度：O(n * n)
     * 空间复杂度：O(1)
     */
    private int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private int[] twoSum2(int[] nums, int target) {
        int capacity = (int) ((float) nums.length / 0.75F + 1.0F);
        Map<Integer, Integer> map = new HashMap<>(capacity);
        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            Integer result = map.get(find);
            if (result != null) {
                return new int[]{result, i};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
