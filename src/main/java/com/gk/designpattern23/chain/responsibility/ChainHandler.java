package com.gk.designpattern23.chain.responsibility;

/**
 * Created by akun on 2018/12/19.
 */
public abstract class ChainHandler {

    public int execute(Chain chain) {
        int result = handleRequest();
        System.out.println("handler return result:" + result);
        if (result % 2 == 0) {
            return result;
        }
        return chain.process();
    }

    protected abstract int handleRequest();
}
