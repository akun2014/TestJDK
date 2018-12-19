package com.gk.designpattern23.chain.responsibility;

/**
 * Created by akun on 2018/12/19.
 */
public class ChainHandlerC extends ChainHandler {
    @Override
    protected void handleRequest() {
        System.out.println(this.getClass().getCanonicalName());
    }
}
