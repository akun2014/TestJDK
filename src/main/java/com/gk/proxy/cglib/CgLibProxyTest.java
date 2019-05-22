package com.gk.proxy.cglib;

import com.gk.proxy.jdk.Car;
import com.gk.proxy.bean.LazyBean;
import com.gk.proxy.bean.PropertyBean;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by akun on 2018/8/24.
 */
@Slf4j
public class CgLibProxyTest {

    @SuppressWarnings("unchecked")
    public <T> T proxy(T t) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t.getClass());
        enhancer.setCallbacks(new Callback[]{new CgLibProxyByMethodInterceptor(t), new CgLibProxy(t)});
        enhancer.setCallbackFilter(new CgLibProxyFilter());
        return (T) enhancer.create();
    }

    static class CgLibProxy implements InvocationHandler {
        private Object srcObject;

        public CgLibProxy(Object srcObject) {
            this.srcObject = srcObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("proxy:{}", proxy.getClass());
            log.info("method:{}", method.getName());
            log.info("args:{}", args);
            log.info("invoke doing...");
            Object invoke = method.invoke(srcObject, args);
            log.info("invoke finished");
            log.info("------------------------------");
            return invoke;
        }
    }

    static class CgLibProxyByMethodInterceptor implements MethodInterceptor {
        private Object srcObject;

        public CgLibProxyByMethodInterceptor(Object srcObject) {
            this.srcObject = srcObject;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//            proxy.invoke(srcObject, args);
//            proxy.invokeSuper(obj, args);
            log.info("obj:{}", obj.getClass());
            log.info("method:{}", method.getName());
            log.info("args:{}", args);
            log.info("intercept doing...");
            Object invoke = method.invoke(srcObject, args);
            log.info("intercept finished");
            log.info("------------------------------");
            return invoke;
        }

    }

    static class CgLibProxyFilter implements CallbackFilter {

        @Override
        public int accept(Method method) {
            if ("move".equals(method.getName())) {
                return 1;
            }
            return 0;
        }
    }

    @Test
    public void proxyTest() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/boyu/IdeaProjects/TestJDK");
        Car proxyCar = proxy(new Car());
        proxyCar.move();
        proxyCar.speedUp(2);
    }

    @Test
    public void test() {
        LazyBean lazyBean = new LazyBean("lazyBean");

        PropertyBean propertyBean = lazyBean.getPropertyBean();
        log.info("{}", propertyBean.getKey());

        PropertyBean propertyBean1 = lazyBean.getPropertyBean();
        log.info("{}", propertyBean1.getKey());


        PropertyBean propertyBeanDispatcher = lazyBean.getPropertyBeanDispatcher();
        log.info("{}", propertyBeanDispatcher.getKey());

        PropertyBean propertyBeanDispatcher1 = lazyBean.getPropertyBeanDispatcher();
        log.info("{}", propertyBeanDispatcher1.getKey());

    }
}

