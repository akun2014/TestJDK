package com.ownerkaka.testjdk.commons.lang;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

/**
 * @author akun
 * @since 2019-05-04
 */
@Slf4j
public class StopWatchTest {

    @Test
    public void test() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
    }

    @Test
    public void test2() throws Exception {
        StopWatch stopWatch = new StopWatch();
    }

    @Test
    public void test3() throws Exception {
        org.springframework.util.StopWatch stopWatch = new org.springframework.util.StopWatch();
    }
}