package com.gk.exception;

/**
 * Created by akun on 2018/9/2.
 * <p>
 * ExceptionTable.MD  -> exception table
 */
public class ExceptionTableTest {

    public void test() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        try {
            a = 1;
        } catch (Exception e) {
            b = 2;
        } finally {
            c = 3;
        }
        d = 4;
    }
}