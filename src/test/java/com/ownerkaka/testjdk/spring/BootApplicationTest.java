package com.ownerkaka.testjdk.spring;

import com.ownerkaka.testjdk.support.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author akun
 * @since 2019-05-27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = BootApplication.class)
public class BootApplicationTest {

    @Autowired
    RetryService retryService;

    @Test
    public void test() throws Exception {
        String result = retryService.retryTest();
        log.info("result:{}", result);
    }
}