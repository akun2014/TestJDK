package com.ownerkaka.testjdk.designpattern23.visitor;

/**
 * @author akun
 * @since 2019-06-29
 */
interface Mechanic {
    void visit(CarComponent element);

    String getName();
}