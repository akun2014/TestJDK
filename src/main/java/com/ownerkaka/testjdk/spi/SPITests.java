package com.ownerkaka.testjdk.spi;

import com.ownerkaka.testjdk.spi.service.MySPIService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author akun
 * @since 2019-07-17
 */
@Slf4j
public class SPITests {

    @Test
    public void test() {
        ServiceLoader<MySPIService> serviceLoader = ServiceLoader.load(MySPIService.class);
        Iterator<MySPIService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            MySPIService next = iterator.next();
            next.bar("ServiceLoader");
        }
    }
}