package com.ownerkaka.testjdk.designpattern23.command;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author akun
 * @since 2019-06-29
 */
@Slf4j
public class CommandTest {
    // This test method is a client
    @Test
    public void test() {
        Administrator admin = new Administrator();
        Server server = new Server();

        // start Apache
        admin.setCommand(new StartApache(server));
        admin.typeEnter();

        // start Tomcat
        admin.setCommand(new StartTomcat(server));
        admin.typeEnter();

        // check executed commands
        int executed = server.getExecutedCommands().size();
        assertTrue("Two commands should be executed but only " +
                executed + " were", executed == 2);
    }
}