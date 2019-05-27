package com.ownerkaka.testjdk.spring.tx;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by akun on 2019/3/6.
 */
@Slf4j
public class JdbcTest {


    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-tx.xml");

        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM mars_app");
        System.out.println(list);
    }
}
