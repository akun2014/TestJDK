package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by akun on 2019-03-26.
 */
@Slf4j
public class MinStack_155 {


    int[] stack = new int[3];
    int index = -1;
    int minIndex = 0;

    public void push(int x) {
        stack[++index] = x;
        if (index + 1 == stack.length) {
            stack = Arrays.copyOf(stack, stack.length + 10);
        }
        if (stack[minIndex] > stack[index]) {
            minIndex = index;
        }
    }

    public void pop() {
        if (minIndex == index) {
            int min = stack[index - 1];
            minIndex = index - 1;
            for (int i = 0; i < index - 1; i++) {
                if (min > stack[i]) {
                    minIndex = i;
                }
            }
        }
        --index;
    }

    public int top() {
        return stack[index];
    }

    public int getMin() {
        if (minIndex <= index) {
            return stack[minIndex];
        }
        int min = stack[index];
        minIndex = index;
        for (int i = 0; i < index; i++) {
            if (min > stack[i]) {
                minIndex = i;
            }
            min = Math.min(min, stack[i]);
        }
        return min;
    }

    //[null,null,null,-10,-10,null,-20,-20,-20,-20,null,null,null,-10,null,null,-7,-10,null]
    //["MinStack","push","push","getMin","getMin","push","getMin","getMin","top","getMin","pop","push","push","getMin","push","pop","top","getMin","pop"]
    //[[],[-10],[14],[],[],[-20],[],[],[],[],[],[10],[-7],[],[-7],[],[],[],[]]
    @Test
    public void test() throws Exception {
        MinStack_155 minStack = new MinStack_155();

        minStack.push(-10);
        minStack.push(14);
        log.info("min：{}", minStack.getMin());
        log.info("min：{}", minStack.getMin());
        minStack.push(-20);
        log.info("min：{}", minStack.getMin());
        log.info("min:{}", minStack.getMin());
        log.info("top:{}", minStack.top());
        log.info("min:{}", minStack.getMin());
        minStack.pop();
        minStack.push(10);
        minStack.push(-7);
        log.info("min:{}", minStack.getMin());
        minStack.push(-7);
        minStack.pop();
        log.info("top:{}", minStack.top());
        log.info("min:{}", minStack.getMin());
        minStack.pop();
    }
}
