package com.ownerkaka.testjdk.spring.aop.advice.annotation;

import java.lang.annotation.*;

/**
 * @author akun
 * @since 2019-07-18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyPointCutAnnotation {
}
