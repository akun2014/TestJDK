package com.gk.jdk8.stream;

import com.gk.support.bean.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by akun on 2017/5/13.
 */
public class StreamTest {
    List<String> stringList;
    List<Integer> integerList;
    List<Long> longList;
    List<User> users;
    Stream<List<Integer>> listStream;

    @Before
    public void build() {
        String str1 = "a"+RandomStringUtils.randomAlphanumeric(7)+"-1";
        String str2 = "b"+RandomStringUtils.randomAlphanumeric(7)+"-2";
        String str3 = "c"+RandomStringUtils.randomAlphanumeric(7)+"-3";
        String str4 = "d"+RandomStringUtils.randomAlphanumeric(6)+"-4";
        stringList = Arrays.asList(str1, str2, str3, str4);

        integerList = Arrays.asList(3, 6, 8, 9);
        longList = Arrays.asList(3L, 6L, 8L, 9L);

        users = Arrays.asList(new User(22,"gk","M"),new User(24,"gk2","F"),new User(12,"gk3","M"));

        listStream = Stream.of(
                Arrays.asList(1), Arrays.asList(1, 2, 3), Arrays.asList(3, 6, 8)
        );

//        IntStream
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

    @Test
    public void testCompator() {
//        String reverseString = stringList.stream().min(Comparator.reverseOrder()).get();
//        stringList.stream().max(Comparator.comparingLong(value -> value.length()));
//        longList.stream().max(Comparator.comparingLong(Long::longValue)).get();
//        longList.stream().mapToInt(i -> i.intValue()).max();
//        stringList.stream().max(Comparator.comparingInt(value -> value.length()));
//        System.out.println(reverseString);


//        stringList.parallelStream().sorted().forEach(s-> System.out.println(s));
//        stringList.parallelStream().sorted(Comparator.naturalOrder()).forEachOrdered(s -> System.out.println(s));
//        stringList.parallelStream().sorted(Comparator.comparingInt(value -> Integer.parseInt(String.valueOf(value.charAt(value.length() - 1))))).forEachOrdered(s -> System.out.println(s));
//        stringList.parallelStream().sorted(Comparator)
        users.parallelStream()
                .sorted(Comparator.comparing(User::getAge))
                .forEach(user -> System.out.print(user.getName()));
        System.out.println( 13/2);
    }

    public void testGroupingby() {
        Map<String, List<User>> collect = users.parallelStream().collect(groupingBy(User::getName));
    }

    @Test
    public void testFlatMap() {
        Stream<Integer> integerStream = listStream.flatMap(integers -> integers.stream());
    }

    public void print(Object object) {
        System.out.println(object);
    }


    @Test
    public void collectTest() {
        StringBuilder builder = stringList.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        stringList.stream().collect(joining("1","",""));
    }

    @After
    public void end() {

        IntStream.range(1, 10).forEach(System.out::print);
        IntStream.range(1, 19).forEach(this::print);
        stringList.stream().map(Integer::parseInt);
        stringList.parallelStream().filter(str -> str.length() > 9).map(str -> str.indexOf(1));
        stringList.parallelStream().reduce("", String::concat);
        stringList.parallelStream().filter(java.util.Objects::nonNull).map(String::toUpperCase).collect(toList());


    }
}
