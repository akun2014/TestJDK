package com.gk.jdk8.stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by akun on 2017/5/13.
 */
public class StreamTest {
    List<String> stringList;
    List<Integer> integerList;

    @Before
    public void build() {
        String str1 = "a"+RandomStringUtils.randomAlphanumeric(7)+"-1";
        String str2 = "b"+RandomStringUtils.randomAlphanumeric(7)+"-2";
        String str3 = "c"+RandomStringUtils.randomAlphanumeric(7)+"-3";
        String str4 = "d"+RandomStringUtils.randomAlphanumeric(6)+"-4";
        stringList = Arrays.asList(str1, str2, str3, str4);

        integerList = Arrays.asList(3, 6, 8, 9);
    }


    @Test
    public void testParallelStream() {
        Stream<String> parallelStream = stringList.parallelStream();
//        List<String> stringList2 = parallelStream.collect(Collectors.toList());
//        parallelStream.count();
//        parallelStream.min(Comparator.comparing(s -> -1));
//        parallelStream.findAny().ifPresent(str ->{
//            System.out.println(str);
//        });
        Stream<String> distinct = parallelStream.distinct();
        distinct.peek(randomStr ->{
            System.out.println("random is " + randomStr);
        }).sorted().forEachOrdered(randomStr -> {
            System.out.println("random after order is " + randomStr);
        });
    }

    @After
    public void end() {

    }
}
