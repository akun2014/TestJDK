package com.ownerkaka.testjdk.support.service.cyclereference;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author akun
 * @since 2019-06-12
 */
@Slf4j
@Service
public class CService implements BeanNameAware {

    @Autowired
    AService aService;

    public void test() {
        log.info("test method in {}", name);
    }

    @Getter
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}