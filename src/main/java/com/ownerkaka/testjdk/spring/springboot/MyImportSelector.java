package com.ownerkaka.testjdk.spring.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author akun
 * @since 2019-07-29
 */
@Slf4j
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.ownerkaka.testjdk.support.bean.Person"};
    }
}