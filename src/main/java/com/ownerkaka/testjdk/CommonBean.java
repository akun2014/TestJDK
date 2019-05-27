package com.ownerkaka.testjdk;

import org.junit.Test;

/**
 * Created by akun on 2018/5/14.
 */
public class CommonBean {

    @Test
    public void test() {
        String str1 = "hello";
        String str2 = "he" + "llo";
        // String str2 = "he" + new String("llo");
        // String str2 = new String("he") + new String("llo");
        // str2 = str2.intern();
        System.out.println(str1 == str2);
    }

    @Test
    public void charTest() throws Exception {
        char c1 = '\uffff';
        char c2 = 'a';
        char c3 = (char) 2147483647;
        char c4 = (char) 0xFFFFFFFFFFFFFFFFL;
        char c5 = (char) 65535;
        char c6 = '我';
        char c7 = 25105;
        int num = 97 + 65535;
        char c8 = (char) num;
        char c9 = 65535;
        System.out.println(Character.toString(c8));
        String name = Character.getName(97);
        String name2 = Character.getName(25105);
        System.out.println(name);
        System.out.println(name2);
    }

    @Test
    public void test4() {
        int math = math(5);
        System.out.println(math);
    }

    public int math(int i) {
        try {
            ++i;
            throw new RuntimeException("");
        } catch (Exception e) {
            i++;
            return i;
        } finally {
            return ++i;
        }
    }
}
