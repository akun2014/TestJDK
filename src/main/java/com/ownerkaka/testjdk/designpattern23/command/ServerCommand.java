package com.ownerkaka.testjdk.designpattern23.command;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 * commands
 */
@Slf4j
abstract class ServerCommand {
    protected Server server;

    public ServerCommand(Server server) {
        this.server = server;
    }

    public abstract void execute();
}