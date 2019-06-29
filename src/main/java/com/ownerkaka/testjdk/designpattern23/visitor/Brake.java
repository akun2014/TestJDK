package com.ownerkaka.testjdk.designpattern23.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
class Brake extends CarComponent {
    @Override
    public void accept(Mechanic mechanic) {
        log.info("Brake visited by:{}", mechanic.getName());
    }
}