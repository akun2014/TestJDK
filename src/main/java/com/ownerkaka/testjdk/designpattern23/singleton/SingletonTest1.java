package com.ownerkaka.testjdk.designpattern23.singleton;

/**
 * Created by akun on 2018/5/11.
 * 懒汉模式，线程不安全
 */
public class SingletonTest1 {
    private static SingletonTest1 instance = null;

    private SingletonTest1() {

    }

    public static SingletonTest1 getInstance() {
        if (instance == null) {
            instance = new SingletonTest1();
        }
        return instance;
    }
}
