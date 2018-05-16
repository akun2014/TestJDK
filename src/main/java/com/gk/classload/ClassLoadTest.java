package com.gk.classload;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by akun on 2018/5/7.
 */
@Slf4j
public class ClassLoadTest {

    static int num = 0;

    static {
        if (true) {
            while (true) {
                num = 1;
            }
        }
    }

    @Getter
    @Setter
    private int age;

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
        Class<?> name = Class.forName("com.gk.classload.ClassLoadTest", true, this.getClass().getClassLoader());
        ClassLoadTest o = (ClassLoadTest) name.newInstance();
        o.setAge(1);
        log.info("age:{}", o.getAge());

    }
}
