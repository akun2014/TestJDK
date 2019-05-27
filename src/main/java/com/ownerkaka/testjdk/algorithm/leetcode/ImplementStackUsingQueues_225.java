package com.ownerkaka.testjdk.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by akun on 2019/3/28.
 * push to back, peek/pop from front, size, 和 is empty
 */
public class ImplementStackUsingQueues_225 {


    Deque<Integer> stack = new LinkedList<>();

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        stack.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return stack.pollLast();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return stack.peekLast();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return stack.isEmpty();
    }
}
