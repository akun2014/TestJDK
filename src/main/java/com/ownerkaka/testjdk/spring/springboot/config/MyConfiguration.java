package com.ownerkaka.testjdk.spring.springboot.config;

import com.ownerkaka.testjdk.support.bean.Human;
import com.ownerkaka.testjdk.support.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author akun
 * @since 2019-07-29
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({HumanProperties.class})
public class MyConfiguration {

    @Configuration
    @AutoConfigureBefore({MyConfig2.class})
    public static class MyConfig1 {

        @Autowired
        HumanProperties humanProperties;

        @Bean
        public Human newHuman() {
            Human human = new Human();
            BeanUtils.copyProperties(humanProperties, human);
            return human;
        }
    }

    @Configuration
    @ConditionalOnBean({Human.class})
    public static class MyConfig2 {
        @Bean
        public Person person() {
            return new Person();
        }

    }

    @Configuration
    @AutoConfigureAfter({MyConfig2.class})
    public static class MyConfig3 {

    }

    @Configuration
    @ConditionalOnBean({Human.class, Person.class})
    public static class MyConfig4 {

    }
}