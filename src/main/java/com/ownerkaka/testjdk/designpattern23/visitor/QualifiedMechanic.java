package com.ownerkaka.testjdk.designpattern23.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
class QualifiedMechanic implements Mechanic {

    @Override
    public void visit(CarComponent component) {
        component.accept(this);
        if (component instanceof Brake) {
            component.setBroken(true);
        }
    }

    @Override
    public String getName() {
        return "qualified";
    }
}