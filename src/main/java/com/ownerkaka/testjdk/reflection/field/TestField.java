package com.ownerkaka.testjdk.reflection.field;

import com.ownerkaka.testjdk.reflection.service.TestFields;
import lombok.Getter;
import org.junit.Test;

import java.lang.reflect.Field;

public class TestField {

    public static void main(String[] args) {

        Class<TestFields> fields = TestFields.class;
        test3(fields);
    }

    public static void test1(Class<TestFields> field) {
        Field[] fields = field.getFields();
        TestFields name = new TestFields();
        try {
            fields[0].set(name, "nan");
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(name.getSex());
        System.out.println(fields.length);
    }

    public static void test2(Class<TestFields> field) {
        Field[] fields = field.getDeclaredFields();
        System.out.println(fields.length);
    }

    public static void test3(Class<TestFields> field) {
        Field fields;
        try {
            fields = field.getDeclaredField("name");
            fields.setAccessible(true);
            System.out.println(fields.get(new TestFields()));
        } catch (NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void test4(Class<TestFields> field) {
        Field fields;
        try {
            fields = field.getDeclaredField("STR");
            fields.setAccessible(true);
            fields.set(new TestFields(), "asd");
            System.out.println();
        } catch (NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 通过反射get/set private field
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        Bean bean = new Bean();
        Field age = bean.getClass().getDeclaredField("age");
        age.setAccessible(true);
        int anInt = age.getInt(bean);
        System.out.println(anInt);
        age.setInt(bean, 20);


        System.out.println(age.getInt(bean));
    }

    static class Bean {
        @Getter
        private String name = "guikun";
        private int age = 26;
    }

}
