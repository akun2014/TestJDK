package com.gk;


import com.gk.support.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by akun on 2019/3/13.
 * spring test usage
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringJUnitWebConfig(locations = "/application.xml")
@TestPropertySource("/application.properties")
public class BaseJunit4Test {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WebApplicationContext wac;

    @Test
    public void test() throws Exception {
        User bean = applicationContext.getBean(User.class);
        Assert.notNull(bean,"bean not null");
        System.out.println(wac.getApplicationName());
        wac.getApplicationName();
    }
}
