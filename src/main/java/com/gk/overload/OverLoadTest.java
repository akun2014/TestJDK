package com.gk.overload;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by akun on 2018/5/15.
 * 重载方法在编译期就确定，重载通过参数的静态类型作为判断依据
 * Human man = new Man()
 * Human 称为变量的静态类型 Man称为变量的实际类型
 * 变量本身的静态类型不可变，但是变量的实际类型在运行时才确定
 */
@Slf4j
public class OverLoadTest {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Women extends Human {
    }

    public void say(Human human) {
        print("human");
    }

    public void say(Man man) {
        print("man");
    }

    public void say(Women women) {
        print("women");
    }

    private void print(String word) {
        log.info("say {}", word);
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();

        OverLoadTest overLoadTest = new OverLoadTest();
        overLoadTest.say(man);
        overLoadTest.say(women);
    }
}
