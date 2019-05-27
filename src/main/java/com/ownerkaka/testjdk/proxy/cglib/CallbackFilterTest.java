package com.ownerkaka.testjdk.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class CallbackFilterTest implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if ("specialName".equals(method.getName())) {
            return 2;
        }
        return 1;
    }
}
