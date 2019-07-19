package com.ownerkaka.testjdk.designpattern23.singleton;

/**
 * Created by akun on 2018/5/11.
 */
public class SingletonTest5 {
    private volatile static SingletonTest5 instance;

    private SingletonTest5() {

    }

    public static SingletonTest5 getInstance() {
        if (instance == null) {
            synchronized (SingletonTest5.class) {
                if (instance == null) {
                    instance = new SingletonTest5();
                }
            }
        }
        return instance;
    }
}