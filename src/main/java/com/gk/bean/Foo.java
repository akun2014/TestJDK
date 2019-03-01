package com.gk.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by akun on 2019/2/27.
 */
@Data
@Component
public class Foo {
    private String type;
    private User user;
    private List<String> member;
}
