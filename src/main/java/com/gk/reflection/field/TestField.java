package com.gk.reflection.field;

import java.lang.reflect.Field;

import com.gk.reflection.service.TestFields;

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
}
