package com.gk.algorithm.leetcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author akun
 * @date 2019-03-30
 */
@Slf4j
public class AddTwoNumbers_2 {


    @AllArgsConstructor
    @Getter
    public static class ListNode {
        int val;
        ListNode next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> l0 = new LinkedList<>();
        int temp = 0;

        while (true) {
            int v1 = l1.getVal();
            int v2 = l2.getVal();
            temp = (temp + v1 + v2) / 10;

        }


//        return null;
    }

    @Test
    public void test() throws Exception {


        List<Integer> l1 = new LinkedList<>();
        l1.add(2);
        l1.add(4);
        l1.add(3);
        List<Integer> l2 = new LinkedList<>();
        l2.add(5);
        l2.add(6);
        l2.add(4);

//        ListNode listNode = addTwoNumbers(l1, l2);

    }
}

