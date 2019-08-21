package com.ownerkaka.testjdk.designpattern23.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
class Car extends CarComponent {
    private boolean broken = false;
    private CarComponent[] components;

    Car() {
        components = new CarComponent[]{
                new Wheels(), new Engine(), new Brake()
        };
    }

    @Override
    public void accept(Mechanic mechanic) {
        this.broken = false;
        if ("qualified".equals(mechanic.getName())) {
            int i = 0;
            while (i < components.length && !this.broken) {
                CarComponent component = components[i];
                mechanic.visit(component);
                this.broken = component.isBroken();
                i++;
            }
        }
        // if mechanic isn't qualified, we suppose that
        // he isn't able to see breakdowns and so
        // he considers the car as no broken
        // (even if the car is broken)
    }

    @Override
    public boolean isBroken() {
        return this.broken;
    }
}