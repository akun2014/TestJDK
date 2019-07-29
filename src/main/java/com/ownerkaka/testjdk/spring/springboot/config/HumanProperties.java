package com.ownerkaka.testjdk.spring.springboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author akun
 * @since 2019-07-29
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.human")
public class HumanProperties {
    private String gender;
    private String hairColor;
    private String eyeColor;
}