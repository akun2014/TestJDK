package com.gk.singleton;

/**
 * Created by akun on 2018/5/11.
 * 饿汉模式、在jvm层通过类加载机制实现线程安全
 */
public class SingletonTest3 {
    private static SingletonTest3 instance = new SingletonTest3();

    private SingletonTest3() {

    }

    public static SingletonTest3 getInstance() {
        return instance;
    }
}
