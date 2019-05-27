package com.ownerkaka.testjdk.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by akun on 2019/3/9.
 */
@Slf4j
public class ReverseTest {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(123, reverse(321));
    }

    /**
     * 反转一个有符号整数
     */
    private int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

}
