package com.ownerkaka.testjdk.algorithm.leetcode;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akun on 2019/1/23.
 *
 * @implSpec https://leetcode-cn.com/problems/two-sum/
 * nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {

    @Test
    public void test() {
        int[] nums = new int[]{2, 7, 11, 15, 3, 6, 1};
        int target = 3;

        int[] sum1 = twoSum(nums, target);
        System.out.println(Arrays.toString(sum1));


    }

    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> bucket = Maps.newHashMapWithExpectedSize(4);
        for (int i = 0; i < nums.length; i++) {
            int a = target - nums[i];
            if (bucket.get(a) != null && bucket.get(a) != i) {
                return new int[]{bucket.get(a), i};
            }
            bucket.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(8);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
