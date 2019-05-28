package com.ownerkaka.testjdk.jvm.classloader;

import com.ownerkaka.testjdk.support.bean.User;

import java.io.*;

public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            name = "com.ownerkaka.testjdk.support.bean.User";
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        System.out.println("classPath:" + path);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }

    public static void main(String[] args) {
        try {
            Class<?> clazz = new FileSystemClassLoader("target/classes/com/gk/bean").findClass("User");
            Object obj1 = clazz.newInstance();
            System.out.println(clazz.getName());

            Class<?> clazz2 = new FileSystemClassLoader("target/classes/com/gk/bean").findClass("User");
            Object obj2 = clazz2.newInstance();
            System.out.println(clazz2.getName());

            System.out.println("Customer class loader:" + clazz.getClassLoader().toString());

            System.out.println(obj1 instanceof User);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
