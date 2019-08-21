package com.ownerkaka.testjdk.spring;

import com.ownerkaka.testjdk.spring.springboot.config.MyConfiguration;
import com.ownerkaka.testjdk.support.bean.Human;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author akun
 * @since 2019-07-29
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MyConfiguration.class})
@TestPropertySource("/application-human.properties")
public class ConfigurationTests {

    @Autowired
    @Qualifier("newHuman")
    Human human;

    @Value("${spring.human.gender:F}")
    String gender;

    @Test
    public void test() {
        Assert.assertEquals("M", human.getGender());
    }

    @Test
    public void test2() {
        Assert.assertEquals("M", gender);
    }
}