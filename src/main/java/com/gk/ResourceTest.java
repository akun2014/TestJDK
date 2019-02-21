package com.gk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
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
        UrlResource resource = new UrlResource(filepath);
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
}
