package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Stack;

/**
 * @author akun
 * @date 2019-03-31
 */
@Slf4j
public class BaseballGame_682 {


    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (String op : ops) {
            if ("C".equals(op)) {
                result = result - stack.pop();
            } else if ("D".equals(op)) {
                result += stack.push(stack.peek() * 2);
            } else if ("+".equals(op)) {
                Integer topItem = stack.pop();
                Integer minorItem = stack.isEmpty() ? 0 : stack.peek();
                Integer currentScore = topItem + minorItem;
                stack.push(topItem);
                stack.push(currentScore);
                result += stack.peek();
            } else {
                result += stack.push(Integer.parseInt(op));
            }
        }
        return result;
    }

    @Test
    public void test() throws Exception {
        /**
         * 输入列表的大小将介于1和1000之间。
         * 列表中的每个整数都将介于-30000和30000之间。
         */
//        String[] ops = new String[]{"5", "2", "C", "D", "+"};
        String[] ops = new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"};
        int points = calPoints(ops);
        log.info("{}", points);
    }

}
