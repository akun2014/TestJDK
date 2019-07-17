package com.ownerkaka.testjdk.spi.service.impl;

import com.ownerkaka.testjdk.spi.service.MySPIService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-07-04
 */
@Slf4j
public class BarSPIServiceImpl implements MySPIService {

    public BarSPIServiceImpl() {
        log.info("bar spi service init");
    }

    @Override
    public void bar(String name) {
        log.info("bar say hello {}", name);
    }
}