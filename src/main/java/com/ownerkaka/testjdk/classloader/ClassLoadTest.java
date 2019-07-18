package com.ownerkaka.testjdk.classloader;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by akun on 2018/5/7.
 */
@Slf4j
public class ClassLoadTest {


    @Getter
    @Setter
    private int age;


    /**
     * Bootstrap ClassLoader (path:${JAVA_HOME}/jre/lib)
     *   sun.misc.Launcher$ExtClassLoader (path:${JAVA_HOME}/jre/ext)
     *      sun.misc.Launcher$AppClassLoader (path:CLASS_PATH)
     */
    @Test
    public void ClassLoadTreeTest() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        while (classLoader != null) {
            log.info("clzz loader:{}", classLoader.getClass());
            classLoader = classLoader.getParent();
        }
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            log.info("url:{}", url.getPath());
        }
    }

    @Test
    public void loadClassByClassMethod() throws Exception {
        Class<?> name = Class.forName("com.ownerkaka.testjdk.classloader.ClassLoadTest", true, this.getClass().getClassLoader());
        ClassLoadTest o = (ClassLoadTest) name.newInstance();
        o.setAge(1);
        Assert.assertEquals(1, o.getAge());
    }
}
