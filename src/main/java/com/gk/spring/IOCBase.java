package com.gk.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.StaticApplicationContext;
/**
 * Created by akun on 2019/3/14.
 */
@Slf4j
public class IOCBase {
    protected final StaticApplicationContext applicationContext = new StaticApplicationContext();
}
