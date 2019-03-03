package com.gk.proxy.cglib;

import com.gk.proxy.Car;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.InterfaceMaker;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class InterfaceMakerTest {

    @Test
    public void test() {
        InterfaceMaker maker = new InterfaceMaker();
        maker.add(Car.class);
        Class targetClazz = maker.create();
        log.info("clazz:{}", targetClazz.toString());
        for (Method method : targetClazz.getMethods()) {
            log.info("method name:{}", method.getName());
        }
    }
}
