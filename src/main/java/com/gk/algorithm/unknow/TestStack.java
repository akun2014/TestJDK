package com.gk.algorithm.unknow;

import java.util.Stack;

/**
 * 判断回文
 *
 * @author gk
 */
public class TestStack {

    public static void main(String[] args) {

        String str = "xyzyx";
        Stack<String> stack = new Stack<String>();

        int mid = str.length() / 2;
        for (int i = 0; i < mid; i++) {
            String pushStr = String.valueOf(str.charAt(i));
            stack.push(pushStr);
        }
        boolean flag = true;
        for (int j = mid + 1; j < str.length(); j++) {
            String popStr = String.valueOf(str.charAt(j));
            if (!stack.pop().equals(popStr))
                flag = false;
        }
        if (!flag) {
            System.out.println("NO");
        } else {
            System.out.println("Yes");
        }

    }

}
