package com.gk.bean;


/**
 * Created by akun on 2018/5/31.
 */
public class TestBean {
    public static void main(String[] args) {
        User.A a = new User.A();
        User user = new User();

        User.CC c = user.new CC();
    }
}
