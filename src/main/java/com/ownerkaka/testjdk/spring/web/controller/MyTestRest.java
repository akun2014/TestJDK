package com.ownerkaka.testjdk.spring.web.controller;

import com.ownerkaka.testjdk.support.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akun on 2019/8/7.
 */
@Slf4j
@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MyTestRest {

    @GetMapping("/person")
    public Person person() {
        Person person = new Person();
        person.setName("myperson");
        return person;
    }
}
