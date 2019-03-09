package com.gk.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by akun on 2019/2/26.
 */
public class LengthOfLongestSubstring_3 {

    @Test
    public void test() {
//        String str = "abcabcbb"; // "abc"
//        String str = "sb"; // "abc"
        String str = "pwwkew"; // "abc"
        int length = lengthOfLongestSubstring(str);
        Assert.assertEquals(3, length);
    }


    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (!set.add(s.charAt(j))) {
                    maxLength = Math.max(maxLength, j - i);
                    set.remove(s.charAt(i));
                    break;
                }
                if (j == s.length() - 1) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        return maxLength;
    }
}
