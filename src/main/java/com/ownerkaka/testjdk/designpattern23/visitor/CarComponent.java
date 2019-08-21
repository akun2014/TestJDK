package com.ownerkaka.testjdk.designpattern23.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
abstract class CarComponent {
    protected boolean broken;

    public abstract void accept(Mechanic mechanic);

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public boolean isBroken() {
        return this.broken;
    }
}