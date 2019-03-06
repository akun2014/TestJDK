package com.gk.spring;

import com.gk.support.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by akun on 2019/2/28.
 */
@Configuration
public class ConfigurationTest {

    @Bean
    public Person person() {
        return new Person();
    }
}
