package com.ownerkaka.testjdk.support.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

/**
 * @author akun
 * @since 2019-05-27
 */
@Slf4j
@Service
public class RetryService {

    @Retryable(value = {TimeoutException.class}, maxAttempts = 4, backoff = @Backoff(delay = 3000L, multiplier = 2))
    public String retryTest() throws TimeoutException {
        log.info("invoker retry method");
        int i = RandomUtils.nextInt(1, 7);
        log.info("i={}", i);
        if (i < 8) {
            throw new TimeoutException("i:" + i);
        }
        return "retryTest";
    }

    @Recover
    public String recoverTest(TimeoutException e) {
        log.info("recoverTest invoked {}", e.getMessage());

        return "recoverTest";
    }
}