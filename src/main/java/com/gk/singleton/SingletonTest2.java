package com.gk.singleton;

/**
 * Created by akun on 2018/5/11.
 * 懒汉模式，通过synchronized关键字上线线程安全
 */
public class SingletonTest2 {
    private static SingletonTest2 instance = null;

    private SingletonTest2() {

    }

    public static synchronized SingletonTest2 getInstance() {
        if (instance == null) {
            instance = new SingletonTest2();
        }
        return instance;
    }
}
