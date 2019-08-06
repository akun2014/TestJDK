package com.ownerkaka.testjdk.collection.set;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * Created by akun on 2019/8/6.
 */
@Slf4j
public class SetUtil {

    public static void addElement(Set<String> set) {
        set.add("aa");
        set.add("bb");
        set.add("ca");
        set.add("cb");
        set.add("daa");
        set.add("da");
    }
}
