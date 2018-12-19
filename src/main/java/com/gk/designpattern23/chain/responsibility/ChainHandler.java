package com.gk.designpattern23.chain.responsibility;

/**
 * Created by akun on 2018/12/19.
 */
public abstract class ChainHandler {

    public void execute(Chain chain) {
        handleRequest();
        chain.process();
    }

    protected abstract void handleRequest();
}
