package com.ownerkaka.testjdk.designpattern23.chain.responsibility;

import java.util.Random;

/**
 * Created by akun on 2018/12/19.
 */
public class ChainHandlerA extends ChainHandler {
    @Override
    protected int handleRequest() {
        System.out.println(this.getClass().getSimpleName());
        return new Random().nextInt();
    }
}
