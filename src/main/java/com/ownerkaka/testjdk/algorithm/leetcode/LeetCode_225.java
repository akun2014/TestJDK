package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author akun
 * @date 2019-03-30
 * Implement-Stack-using-Queues
 * push to back, peek/pop from front, size, 和 is empty
 */
@Slf4j
public class LeetCode_225 {


    Queue<Integer> queue = new ArrayDeque<>();
    Queue<Integer> temp = new ArrayDeque<>();

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        while (queue.size() != 1) {
            temp.add(queue.poll());
        }
        Integer result = queue.poll();

        while (!temp.isEmpty()) {
            queue.add(temp.poll());
        }
        return result;
    }

    public int top() {
        while (queue.size() != 1) {
            temp.add(queue.poll());
        }
        Integer result = queue.peek();
        temp.add(queue.poll());

        while (!temp.isEmpty()) {
            queue.add(temp.poll());
        }
        return result;
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    @Test
    public void test() throws Exception {


    }

}
