package com.ownerkaka.testjdk.designpattern23.command;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 * invoker
 */
@Slf4j
class Administrator {
    private ServerCommand command;

    public void setCommand(ServerCommand command) {
        this.command = command;
    }

    public void typeEnter() {
        this.command.execute();
    }
}