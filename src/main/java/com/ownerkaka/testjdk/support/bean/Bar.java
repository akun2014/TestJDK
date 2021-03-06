package com.ownerkaka.testjdk.support.bean;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by akun on 2019/2/28.
 */
@Component
@Slf4j
@NoArgsConstructor
public class Bar implements InitializingBean, BeanNameAware {
    @Autowired
    @Setter
    private Foo foo;
    private String beanName;

    public Bar(Foo foo) {
        this.foo = foo;
    }


    @Override
    public String toString() {
        return "Bar{" +
                "foo=" + foo +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet method invoke. bean name:{}", beanName);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public void bar() {
        log.info("invoke bar method");
    }

    public String append(String source) {
        return "bar" + source;
    }

    public Human getHuman() {
        return null;
    }
}
