package com.ownerkaka.testjdk.jvm.jit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by akun on 2018/8/10.
 */
@Slf4j
public class JITTest {


    /**
     * 不使用逃逸分析（-DoEscapeAnalysis）、对象将在堆上分配内存
     * -Xmx4G -Xms4G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
     *
     * num     #instances         #bytes  class name
     * ----------------------------------------------
     * 1:          2589       70277240  [I
     * 2:       1000000       16000000  com.ownerkaka.testjdk.jvm.jit.JITTest$User
     * 3:         42662       12138200  [B
     * 4:         53556        6411000  [C
     * 5:         23433         562392  java.lang.String
     * 6:          4184         294480  [Ljava.lang.Object;
     * 7:          1633         184600  java.lang.Class
     */

    /**
     * 使用逃逸分析（+DoEscapeAnalysis）、对象没有逃逸出方法，那么有可能堆内存分配会被优化成栈内存分配
     * -Xmx4G -Xms4G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
     * <p>
     * num     #instances         #bytes  class name
     * ----------------------------------------------
     * 1:          2589       84763544  [I
     * 2:         42662       12138200  [B
     * 3:         53556        6411208  [C
     * 4:         94590        1513440  com.ownerkaka.testjdk.jvm.jit.JITTest$User
     * 5:         23432         562368  java.lang.String
     * 6:          4185         294504  [Ljava.lang.Object;
     * 7:          1633         184600  java.lang.Class
     */

    @Test
    public void test() {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            alloc();
        }
        // 查看执行时间
        long a2 = System.currentTimeMillis();
        System.out.println("cost " + (a2 - a1) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();
    }

    static class User {

    }
}
