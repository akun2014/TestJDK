package com.ownerkaka.testjdk.spring;

import com.ownerkaka.testjdk.support.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author akun
 * @since 2019-05-27
 */
@Slf4j
@EnableRetry
@SpringBootApplication(scanBasePackageClasses = RetryService.class)
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}