package com.ownerkaka.testjdk.classloader;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.loader.WebappClassLoader;
import org.junit.Test;

import java.net.URL;

@Slf4j
public class WebappClassLoaderTests {

    @Test
    public void test() {
        WebappClassLoader webappClassLoader = new WebappClassLoader();

        URL[] urLs = webappClassLoader.getURLs();
        for (URL urL : urLs) {
            log.info("{}", urL.toString());
        }

    }
}
