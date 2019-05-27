package com.ownerkaka.testjdk.designpattern23.chain.responsibility;

import java.util.Arrays;
import java.util.List;

/**
 * Created by akun on 2018/12/19.
 */
public class ChainClient {

    public static void main(String[] args) {
        List<ChainHandler> chainHandlers = Arrays.asList(
                new ChainHandlerA(),
                new ChainHandlerB(),
                new ChainHandlerC());
        for (int i = 0; i < 10; i++) {
            Chain chain = new Chain();
            chain.setHandlerList(chainHandlers);
            int process = chain.process();
            System.out.println("chain finished result:" + process);
        }
    }
}
