package com.gk.support.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by akun on 2019/3/24.
 */
@Data
public class PropertyBean {
    private String name;
    private int age;
    private float amount;
    private List<String> dogs;
    private Set<String> cats;
    private Map<String, Object> lions;
    private String[] monkeys;
    private User user;

    public void init() {
        System.out.println("init method invoked");
    }

    public void destroy() {
        System.out.println("destroy method invoked!");
    }
}
