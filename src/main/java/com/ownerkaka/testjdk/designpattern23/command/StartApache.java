package com.ownerkaka.testjdk.designpattern23.command;

import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
class StartApache extends ServerCommand {
    public StartApache(Server server) {
        super(server);
    }

    @Override
    public void execute() {
        server.launchCommand("sudo service apache2 start");
    }
}