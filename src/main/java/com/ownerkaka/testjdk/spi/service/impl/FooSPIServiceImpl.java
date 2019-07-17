package com.ownerkaka.testjdk.spi.service.impl;

import com.ownerkaka.testjdk.spi.service.MySPIService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-07-04
 */
@Slf4j
public class FooSPIServiceImpl implements MySPIService {

    public FooSPIServiceImpl() {
        log.info("foo spi service init");
    }

    @Override
    public void bar(String name) {
        log.info("foo say hello {}", name);
    }
}