package com.gk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author akun
 * @date 2019-03-30
 */
@Slf4j
public class NextGreaterElementII_503 {

    public int[] nextGreaterElements(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                while (stack.isEmpty() && stack.pop() < nums[i]) {
                    map.put(stack.pop(), nums[i]);
                }
                stack.push(nums[i]);
                stack.push(nums[j]);
            }
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = map.getOrDefault(nums[i], -1);
        }
        return result;
    }

    @Test
    public void test() throws Exception {
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] nextGreaterElements = nextGreaterElements(nums2);
        log.info("{}", Arrays.toString(nextGreaterElements));
    }
}
