package com.ownerkaka.testjdk.proxy.cglib;

import com.ownerkaka.testjdk.proxy.bean.Tesla;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
public class TeslaProxyTest {

    @Test
    public void test() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tesla.class);

        Callback[] callbacks = {NoOp.INSTANCE, new MethodInterceptorTest(), new FixedValueTest(), new InvokerHandlerTest()};
//        enhancer.setCallbacks(callbacks);
//        enhancer.setCallbackFilter(new CallbackFilterTest());

        enhancer.setCallback(new InvokerHandlerTest());

//        Tesla tesla = (Tesla) enhancer.create(); // invoke NoArgsConstructor
        Class[] argTypes = new Class[]{String.class, BigDecimal.class};
        Object[] args = new Object[]{"Model X", new BigDecimal(123)};
        Tesla tesla = (Tesla) enhancer.create(argTypes, args); // invoke AllArgsConstructor
        tesla.description();
        String specialName = tesla.specialName(); // this method will invoke fixedValue callback
        log.info("specialName:{}", specialName);
    }
}
