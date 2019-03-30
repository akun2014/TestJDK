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
public class NextGreaterElement_496 {

    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int[] element = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            boolean flag = false;
            element[i] = -1;
            for (int j = 0; j < nums2.length; j++) {
                if (num == nums2[j]) {
                    flag = true;
                }
                if (nums2[j] > num && flag) {
                    element[i] = nums2[j];
                    break;
                }
            }
        }
        return element;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums2) {
            while (!stack.isEmpty() && stack.peek() < i) {
                map.put(stack.pop(), i);
            }
            stack.push(i);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }


    @Test
    public void test() throws Exception {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] element = nextGreaterElement2(nums1, nums2);
        log.info("{}", Arrays.toString(element));
    }
}
