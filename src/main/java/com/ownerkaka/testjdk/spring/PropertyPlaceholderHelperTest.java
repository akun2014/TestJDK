package com.ownerkaka.testjdk.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Properties;

/**
 * @author akun
 * @since 2019-05-12
 */
@Slf4j
public class PropertyPlaceholderHelperTest {

    @Test
    public void test() throws Exception {
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("[", "]",
                ":", true);

        Properties properties = new Properties();
        properties.setProperty("name", "akun");
        properties.setProperty("hubei", "hb");
        properties.setProperty("hb", "hubei");
        properties.setProperty("hbhm", "hubeihuangmei");

        String placeholders = helper.replacePlaceholders("my nema is [name] i'm age [age:10] i come from [[hubei]hm]", properties);
        Assert.isTrue(placeholders.equalsIgnoreCase("my nema is akun i'm age 10 i come from hubeihuangmei"), "");
    }

    @Test
    public void testPropertySourcesPropertyResolver() throws Exception {
        MutablePropertySources propertySources = new MutablePropertySources();
        ResourcePropertySource resourcePropertySource = new ResourcePropertySource("application.properties");
        propertySources.addLast(resourcePropertySource);

        PropertySourcesPropertyResolver resolver = new PropertySourcesPropertyResolver(propertySources);
        String property = resolver.getProperty("spring.datasource.url");
        Assert.isTrue(property.equalsIgnoreCase("jdbc:mysql://localhost:3306/wesd_mars"), "");
    }
}