package com.ownerkaka.testjdk.proxy.bean;

import com.ownerkaka.testjdk.proxy.cglib.DispatcherTest;
import com.ownerkaka.testjdk.proxy.cglib.LazyLoaderTest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by akun on 2019/3/3.
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LazyBean {
    private String name;
    private PropertyBean propertyBean;
    private PropertyBean propertyBeanDispatcher;

    public LazyBean(String name) {
        this.name = name;
        this.propertyBean = createPropertyBean();
        this.propertyBeanDispatcher = createPropertyBeanDispatcher();
    }

    /**
     * 仅第一次懒加载
     */
    private PropertyBean createPropertyBean() {
        /**
         * 使用cglib进行懒加载 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
         * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，
         * 就会自动触发代理类回调）。
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        PropertyBean pb = (PropertyBean) enhancer.create(PropertyBean.class,
                new LazyLoaderTest());
        return pb;
    }

    /**
     * 每次都懒加载
     */
    private PropertyBean createPropertyBeanDispatcher() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        PropertyBean pb = (PropertyBean) enhancer.create(PropertyBean.class,
                new DispatcherTest());
        return pb;
    }
}