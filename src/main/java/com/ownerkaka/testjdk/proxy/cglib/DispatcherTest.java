package com.ownerkaka.testjdk.proxy.cglib;

import com.ownerkaka.testjdk.proxy.bean.PropertyBean;
import com.ownerkaka.testjdk.proxy.bean.Tesla;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Dispatcher;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class DispatcherTest implements Dispatcher {
    @Override
    public Object loadObject() throws Exception {
        log.info("clazz:{} invoked", this.getClass().getCanonicalName());
        log.info("before dispatch load object");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("lazyLoader");
        propertyBean.setValue(new Tesla());
        log.info("after dispatch load object");
        return propertyBean;
    }
}
