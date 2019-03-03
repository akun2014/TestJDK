package com.gk.proxy.cglib;

import com.gk.proxy.bean.PropertyBean;
import com.gk.proxy.bean.Tesla;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.LazyLoader;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class LazyLoaderTest implements LazyLoader {
    @Override
    public Object loadObject() throws Exception {
        log.info("before lazy load object");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("lazyLoader");
        propertyBean.setValue(new Tesla());
        log.info("after lazy load object");
        return propertyBean;
    }
}
