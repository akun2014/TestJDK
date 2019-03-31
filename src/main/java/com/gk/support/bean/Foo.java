package com.gk.support.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by akun on 2019/2/27.
 */
@Data
@Component
@NoArgsConstructor
public class Foo {
    private String type;
    private User user;
    private List<String> member;
    private Bar bar;

    public Foo(Bar bar, User user) {
        this.bar = bar;
        this.user = user;
    }
}
