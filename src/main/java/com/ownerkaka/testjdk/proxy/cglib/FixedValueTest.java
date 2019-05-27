package com.ownerkaka.testjdk.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.FixedValue;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class FixedValueTest implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        log.info("clazz:{} invoked", this.getClass().getCanonicalName());
        return "fixedValue";
    }
}
