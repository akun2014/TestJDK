package com.ownerkaka.testjdk.tomcat;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

/**
 * @author akun
 * @since 2019-07-04
 */
@Slf4j
public class TomcatTests {

    @Test
    public void testCreateTomcat() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
//        tomcat.setHost("");
        tomcat.setPort(9091);

        Connector connector = new Connector();
        connector.setPort(9091);
        tomcat.setConnector(connector);
        tomcat.start();

    }
}