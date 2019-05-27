package com.ownerkaka.testjdk.guava.objects;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;

/**
 * Created by akun on 2016/10/16.
 */
public class TestObjects {


    @Test
    public void testObjects() {
        boolean equal = Objects.equal(null, null);
        int hashCode = Objects.hashCode("a", new Integer(1));


        int result = ComparisonChain.start()
                .compare(1, 1)
                .compare("asd","aa")
                .result();

    }
}
