package com.ownerkaka.testjdk.classload;

/**
 * Created by akun on 2018/12/18.
 *
 * @see java.net.URLClassLoader
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 如果要打破ClassLoader定义的类加载双亲委派机制，重写loadClass方法，重新定义类加载逻辑
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    /**
     * 沿用ClassLoader定义的类加载双亲委派机制，但是需要自定义类加载路径，重写findClass方法，
     * 定义从指定路径加载类的逻辑
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
