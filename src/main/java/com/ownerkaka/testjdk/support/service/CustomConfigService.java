package com.ownerkaka.testjdk.support.service;

import com.ownerkaka.testjdk.support.bean.Human;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

/**
 * @author akun
 * @since 2019-06-02
 */
@Slf4j
@Configuration
public class CustomConfigService {

    @Bean
    @Description("this is Java-based Configuration")
    public Human human() {
        return new Human();
    }
}