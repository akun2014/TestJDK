package com.gk.spring.ioc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.*;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.support.ServletContextResource;

import java.io.File;
import java.io.IOException;

/**
 * Created by akun on 2019/2/21.
 *
 * @see FileSystemResource
 * @see ClassPathResource
 * @see UrlResource
 * @see ServletContextResource
 */
@Slf4j
public class ResourceTest {

    private String filepath = "/Users/boyu/java_error_in_idea_1200.log";

    @Test
    public void testFileSystemResource() throws IOException {
        FileSystemResource resource = new FileSystemResource(filepath);
        if (resource.exists()) {
            File file = resource.getFile();
            System.out.println(file.getPath());
        }

        resource.isWritable();
        resource.isReadable();

        resource.getInputStream();
        resource.getOutputStream();

        resource.getPath();
    }

    @Test
    public void testClassPathResource() throws IOException {
        ClassPathResource resource = new ClassPathResource("logback.xml");
        if (resource.exists()) {
            File file = resource.getFile();
            System.out.println(file.getPath());
        }

        resource.isReadable();

        resource.getInputStream();
        resource.getPath();
    }

    @Test
    public void testUrlResource() throws IOException {
        UrlResource resource = new UrlResource("file:" + filepath);
        if (resource.exists()) {
            File file = resource.getFile();
            System.out.println(file.getPath());
        }
        resource.isReadable();

        resource.getInputStream();
    }

    public void testServletContextResource() {
        ServletContextResource resource = new ServletContextResource(null, "");
    }

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Test
    public void test() throws Exception {
        Resource resource = resourceLoader.getResource("logback.xml");
        Assert.isTrue("DefaultResourceLoader.ClassPathContextResource".equals(ClassUtils.getShortName(resource.getClass())), "");

        Resource resource1 = resourceLoader.getResource("/Users/boyu/ioc.png");
        Assert.isTrue("DefaultResourceLoader.ClassPathContextResource".equals(ClassUtils.getShortName(resource1.getClass())), "");

        Resource resource2 = resourceLoader.getResource("file:/Users/boyu/ioc.png");
        Assert.isInstanceOf(FileUrlResource.class, resource2);

        Resource resource3 = resourceLoader.getResource("classpath:/Users/boyu/ioc.png");
        Assert.isInstanceOf(ClassPathResource.class, resource3);
    }
}
