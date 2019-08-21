package com.ownerkaka.testjdk.spring;

import com.ownerkaka.testjdk.support.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by akun on 2019/2/28.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigurationTest {

    @Bean
    public Person person() {
        return new Person();
    }
}
