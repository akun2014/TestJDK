package com.ownerkaka.testjdk.designpattern23.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
class NonQualifiedMechanic implements Mechanic {
    @Override
    public void visit(CarComponent element) {
        element.accept(this);
        element.setBroken(true);
    }

    @Override
    public String getName() {
        return "unqualified";
    }
}