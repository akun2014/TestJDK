package com.gk.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by akun on 2019/2/27.
 */
@Slf4j
@Data
public class Foo {
    private String type;
    private User user;
    private List<String> member;
}
