package com.ownerkaka.testjdk.designpattern23.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
class Engine extends CarComponent {
    @Override
    public void accept(Mechanic mechanic) {
        log.info("Engine visited by:{}", mechanic.getName());
    }
}