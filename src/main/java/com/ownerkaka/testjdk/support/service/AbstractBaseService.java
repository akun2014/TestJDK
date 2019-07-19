package com.ownerkaka.testjdk.support.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-07-18
 */
@Slf4j
public abstract class AbstractBaseService {

    public void baseBar(Long num) {
        log.info("baseBar invoke num:{}", num);
    }
}