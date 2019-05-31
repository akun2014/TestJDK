package com.ownerkaka.testjdk.jodatime;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author akun
 * @since 2019-05-31
 */
@Slf4j
public class DateTimeTest {
    @Test
    public void test() throws Exception {
        String pattern = "yyyy-mm-dd MM:hh:ss";
        DateTime lastExpiredDate = new DateTime(1553915953000L).plusDays(7);
        log.info("{}", lastExpiredDate.toString(pattern));
        boolean after = DateTime.now().isAfter(lastExpiredDate);
        boolean before = DateTime.now().isBefore(lastExpiredDate);
        Assert.assertTrue(after);
        Assert.assertTrue(before);
    }
}