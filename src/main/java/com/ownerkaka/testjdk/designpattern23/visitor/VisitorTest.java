package com.ownerkaka.testjdk.designpattern23.visitor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
public class VisitorTest {
    @Test
    public void test() {
        CarComponent car = new Car();
        Mechanic mechanic = new QualifiedMechanic();
        mechanic.visit(car);
        assertTrue("After qualified mechanics visit, the car should be broken",
                car.isBroken());

        Mechanic nonqualifiedMechanic = new NonQualifiedMechanic();
        nonqualifiedMechanic.visit(car);
        assertFalse("Car shouldn't be broken because non qualified mechanic " +
                " can't see breakdowns", car.isBroken());
    }
}