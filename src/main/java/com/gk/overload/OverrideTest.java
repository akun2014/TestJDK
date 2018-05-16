package com.gk.overload;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by akun on 2018/5/15.
 * 重写，在运行时根据实际类型判断
 */
@Slf4j
public class OverrideTest {

    static abstract class Human {
        protected abstract void say();
    }

    static class Man extends Human {
        @Override
        protected void say() {
            log.info("say man");
        }
    }

    static class Women extends Human {
        @Override
        protected void say() {
            log.info("say women");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        man.say();
        women.say();
        man = new Women();
        man.say();
    }

}
