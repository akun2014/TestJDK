package com.gk.spring.ioc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

/**
 * Created by akun on 2019/3/24.
 */
@Slf4j
public class ResourceLoaderTest {

    @Test
    public void testDefaultResourceLoader() throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("logback.xml");
        Assert.isTrue("ClassPathContextResource".equals(resource.getClass().getSimpleName()), "true");
    }


    @Test
    public void testFileSystemResourceLoader() throws Exception {
        ResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource("logback.xml");
        Assert.isTrue("FileSystemContextResource".equals(resource.getClass().getSimpleName()), "true");
    }

    @Test
    public void testClassRelativeResourceLoader() throws Exception {
        ResourceLoader resourceLoader = new ClassRelativeResourceLoader(getClass());
        Resource resource = resourceLoader.getResource("logback.xml");
        Assert.isTrue("ClassRelativeResource".equals(resource.getClass().getSimpleName()), "true");
    }

    @Test
    public void testPathMatchingResourcePatternResolver() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        ResourceLoader resourceLoader = resolver.getResourceLoader();
        Assert.isInstanceOf(DefaultResourceLoader.class, resourceLoader);
        PathMatcher pathMatcher = resolver.getPathMatcher();
        Assert.isInstanceOf(AntPathMatcher.class, pathMatcher);

        Resource[] resources = resolver.getResources("classpath*:*.xml");
    }
}
