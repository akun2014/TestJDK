package com.gk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by akun on 2019/3/20.
 */
@Slf4j
public class RecentCounter_933 {

    @Test
    public void test() throws Exception {

        int[] nums = new int[]{1, 100, 3001, 3002,6002 };

        for (int i = 0; i < nums.length; i++) {
            int ping = ping(nums[i]);
            System.out.println(ping);
        }
    }

    private Deque<Integer> deque = new LinkedList<>();


    public int ping(int t) {
        deque.offer(t);
        int offset = t - 3000;
        deque.removeIf(integer -> integer < offset);

        return deque.size();
    }
}
