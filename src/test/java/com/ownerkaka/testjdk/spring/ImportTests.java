package com.ownerkaka.testjdk.spring;

import com.ownerkaka.testjdk.spring.springboot.config.ImportConfig;
import com.ownerkaka.testjdk.support.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author akun
 * @since 2019-07-29
 */
@Slf4j
@SpringBootTest(classes = {ImportConfig.class})
@RunWith(SpringRunner.class)
public class ImportTests {


    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        Person person = applicationContext.getBean(Person.class);
        assertNotNull(person);
    }
}