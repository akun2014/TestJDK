package com.ownerkaka.testjdk.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;


/**
 * Created by akun on 2018/6/3.
 * Pattern  模式
 * Matcher  匹配器
 */
public class RegexTest {


    final Pattern number = Pattern.compile("\\d+");
    final Pattern word = Pattern.compile("\\w+");

    @Test
    public void test() {
        Matcher matcher = number.matcher("123");
        matcher.group();
        String replace = matcher.replaceAll("replace");
        Matcher matcher1 = word.matcher("abc");

        assertTrue(matcher.matches());
        assertTrue(matcher1.matches());

        assertTrue(replace.equals("replace"));

    }

    @Test
    public void matcherOneTime() throws Exception {
        boolean matches = Pattern.matches("[a-z]*", "guikuns");
        assertTrue(matches);
    }

    Pattern p = Pattern.compile("([a-z]+)(\\d+)");

    @Test
    public void groupTest() throws Exception {
        String txt = "aaa2223bb";
        Matcher matcher = p.matcher(txt);
        assertTrue(matcher.find());
        matcher.reset();
        matcher.lookingAt();
//        String group = matcher.group();
//        assertTrue("123".equals(group));

        System.out.println(matcher.start());
        System.out.println(matcher.end());
        System.out.println(matcher.group());
        matcher.reset();

        while (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group());
        }

        Runtime.getRuntime().gc();


    }
}
