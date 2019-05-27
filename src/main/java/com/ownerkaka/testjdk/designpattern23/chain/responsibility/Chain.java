package com.ownerkaka.testjdk.designpattern23.chain.responsibility;

import java.util.List;

/**
 * Created by akun on 2018/12/19.
 */
public class Chain {
    private List<ChainHandler> handlerList;

    private int index = 0;

    public void setHandlerList(List<ChainHandler> handlerList) {
        this.handlerList = handlerList;
    }

    public int process() {
        if (index >= handlerList.size()) {
            return 2;
        }
        System.out.println("i=" + index);
        return handlerList.get(index++).execute(this);
    }
}
