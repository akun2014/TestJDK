package com.ownerkaka.testjdk.singleton;

/**
 * Created by akun on 2018/5/11.
 */
public class SingletonTest4 {
    private static class SingletonHandler {
        private static final SingletonTest4 instance = new SingletonTest4();
    }

    private SingletonTest4() {

    }

    public static SingletonTest4 getInstance() {
        return SingletonHandler.instance;
    }
}
