package com.gk.bean;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by akun on 2019/2/28.
 */
@Component
public class Bar {
    @Autowired
    @Setter
    private Foo foo;

    @Override
    public String toString() {
        return "Bar{" +
                "foo=" + foo +
                '}';
    }
}
