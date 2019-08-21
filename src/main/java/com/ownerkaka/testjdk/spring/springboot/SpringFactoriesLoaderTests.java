package com.ownerkaka.testjdk.spring.springboot;

import com.ownerkaka.testjdk.support.service.BarService;
import com.ownerkaka.testjdk.support.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author akun
 * @since 2019-07-26
 * 类SPI加载机制
 */
@Slf4j
public class SpringFactoriesLoaderTests {

    @Test
    public void test() {
        List<BarService> barServiceList = SpringFactoriesLoader.loadFactories(BarService.class, this.getClass().getClassLoader());
        Assert.assertEquals(2, barServiceList.size());
        for (BarService barService : barServiceList) {
            Assert.assertNotNull(barService);
        }
        List<String> factoryNames = SpringFactoriesLoader.loadFactoryNames(BarService.class, this.getClass().getClassLoader());
        Assert.assertArrayEquals(factoryNames.toArray(new String[0]),
                new String[]{"com.ownerkaka.testjdk.support.service.impl.BarServiceImpl",
                        "com.ownerkaka.testjdk.support.service.impl.Bar2ServiceImpl"});

        List<FooService> fooServices = SpringFactoriesLoader.loadFactories(FooService.class, this.getClass().getClassLoader());
        Assert.assertEquals(1, fooServices.size());
        for (FooService fooService : fooServices) {
            Assert.assertNotNull(fooService);
        }
    }
}