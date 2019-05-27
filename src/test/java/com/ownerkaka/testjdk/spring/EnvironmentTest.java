package com.ownerkaka.testjdk.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author akun
 * @date 2019-04-14
 */
@Slf4j
@RunWith(SpringRunner.class)
public class EnvironmentTest {

    @Autowired
    Environment environment;

    @Test
    public void test() throws Exception {
        environment.getActiveProfiles();
    }
}