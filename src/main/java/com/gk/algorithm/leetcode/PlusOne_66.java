package com.gk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author akun
 * @date 2019-03-31
 */
@Slf4j
public class PlusOne_66 {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] dest = new int[digits.length + 1];
        dest[0] = 1;

        return dest;
    }

    @Test
    public void test() throws Exception {
        int[] digits = new int[]{9, 9};
        int[] plusOne = plusOne(digits);
        log.info("{}", Arrays.toString(plusOne));
    }
}
