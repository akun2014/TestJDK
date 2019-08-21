package com.ownerkaka.testjdk.designpattern23.command;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author akun
 * @since 2019-06-29
 * receiver
 */
@Slf4j
class Server {
    // as in common terminals, we store executed commands in history
    private List<String> executedCommands = new ArrayList<String>();

    public void launchCommand(String command) {
        System.out.println("Executing: " + command + " on server");
        this.executedCommands.add(command);
    }

    public List<String> getExecutedCommands() {
        return this.executedCommands;
    }
}