package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author akun
 * @date 2019-03-31
 */
@Slf4j
public class BackspaceStringCompare_844 {


    public boolean backspaceCompare(String S, String T) {
        Character[] trimS = trim(S);
        Character[] trimT = trim(T);

        return Arrays.equals(trimS, trimT);
    }

    private Character[] trim(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char at = str.charAt(i);
            if (!stack.isEmpty() && at == '#') {
                stack.pop();
            } else if (at != '#') {
                stack.push(at);
            }
        }
        return stack.toArray(new Character[stack.size()]);
    }

    @Test
    public void test() throws Exception {
        String S = "ab#c", T = "ad#c";
//        String S = "ab##", T = "c#d#";
//        String S = "a#c", T = "b";
        Assert.isTrue(backspaceCompare(S, T), "");
    }
}
