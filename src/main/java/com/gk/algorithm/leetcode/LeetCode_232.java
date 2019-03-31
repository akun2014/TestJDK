package com.gk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * @author akun
 * @date 2019-03-30
 * 0232-Implement-Queue-using-Stacks
 */
@Slf4j
public class LeetCode_232 {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> temp = new Stack<>();

    public void push(Integer integer) {
        stack.push(integer);
    }

    public Integer pop() {
        while (!empty()) {
            temp.push(stack.pop());
        }

        int result = temp.pop();
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return result;
    }


    public Integer peek() {
        while (!empty()) {
            temp.push(stack.pop());
        }

        int result = temp.peek();

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return result;
    }

    public boolean empty() {
        return stack.isEmpty();
    }

}
