package com.ownerkaka.testjdk.spring.springboot.config;

import com.ownerkaka.testjdk.spring.springboot.MyImportSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author akun
 * @since 2019-07-29
 */
@Slf4j
@Configuration
@Import(MyImportSelector.class)
public class ImportConfig {
}