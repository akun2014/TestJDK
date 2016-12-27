package com.gk.io;

import org.apache.commons.io.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by akun on 2016/12/28.
 */
public class CommonIOTest {


    //把字节数组写入文件
    @Test
    public void testIO() throws IOException {
        String temp = "guikun";

        FileUtils.writeByteArrayToFile(new File("test.txt"),temp.getBytes(Charsets.UTF_8));
        FileUtils.writeStringToFile(new File(""),temp, Charsets.UTF_8);
    }

    @Test
    public void testIO2() {
        String filename = "test.txt";
        String baseName = FilenameUtils.getBaseName(filename);//除去文件后缀的文件名
        String prefix = FilenameUtils.getPrefix(filename);
        String extension = FilenameUtils.getExtension(filename);
        String name = FilenameUtils.getName(filename);

        boolean extension1 = FilenameUtils.isExtension(filename, "csv");


        System.out.println("baseName:"+baseName);
        System.out.println("prefix:"+prefix);
        System.out.println("extension:"+extension);
        System.out.println("name:"+name);
        System.out.println("extension1:"+extension1);
    }

    @Test
    public void testIO3() throws IOException {
        long l = FileSystemUtils.freeSpaceKb();
        String s = FileUtils.byteCountToDisplaySize(l);

        System.out.println(s);
    }


}
