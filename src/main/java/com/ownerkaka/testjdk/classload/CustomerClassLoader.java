package com.ownerkaka.testjdk.classload;

import com.ownerkaka.testjdk.CommonBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by akun on 2019/1/8.
 * 通过不同类加载器加载的类，即使类名一致，isInstance 也会返回false
 */
@Slf4j
public class CustomerClassLoader extends URLClassLoader {
    public CustomerClassLoader(URL[] urls) {
        super(urls, ClassLoader.getSystemClassLoader().getParent());
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass != null) {
            return loadedClass;
        }
// 优先从parent（SystemClassLoader）里加载系统类，避免抛出ClassNotFoundException
        if (name != null && (name.startsWith("sun.") || name.startsWith("java."))) {
            return super.loadClass(name, resolve);
        }
        Class<?> aClass = findClass(name);
        if (resolve) {
            resolveClass(aClass);
        }
        return super.loadClass(name, resolve);
    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException {
        URL[] urls = new URL[]{new URL("file:/Users/boyu/IdeaProjects/TestJDK/target/classes/")};
        CustomerClassLoader classLoader = new CustomerClassLoader(urls);
        Class<?> aClass = classLoader.loadClass("com.ownerkaka.testjdk.CommonBean", true);

        String simpleName2 = CommonBean.class.getClassLoader().getClass().getCanonicalName();
        Assert.assertEquals("load by AppClassLoader", "sun.misc.Launcher.AppClassLoader", simpleName2);
        String simpleName3 = aClass.getClassLoader().getClass().getCanonicalName();
        Assert.assertEquals("load by CustomerClassLoader", "com.gk.classload.CustomerClassLoader", simpleName3);

        boolean instance = aClass.isInstance(CommonBean.class);
        Assert.assertFalse(instance);
    }


}
