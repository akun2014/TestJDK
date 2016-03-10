package com.gk.collection.Collections;

import com.gk.common.entity.User;
import org.junit.Test;

import java.util.*;

/**
 * Created by gk499 on 2016/3/7.
 */
public class TestCollections {

    @Test
    public void testSingleton(){
        Set<String> singleton = Collections.singleton("testSingleton");
        List<String> singletonList = Collections.singletonList("singletonList");
        Map<String, String> stringStringMap = Collections.singletonMap("key", "value");
        List<User> userList = Collections.singletonList(new User("guikun", new Integer(20)));

        Iterator<String> iterator1 = singleton.iterator();
        while (iterator1.hasNext()){
            String next = iterator1.next();
            System.out.println(next);
        }
        for (int i = 0; i < singletonList.size(); i++) {
            String s = singletonList.get(i);

            System.out.println(s);
        }

        Set<Map.Entry<String, String>> entrySet = stringStringMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+" "+next.getValue());
        }


        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            System.out.print(user.hashCode());
            System.out.print(user.getUsername());
            System.out.println(user.getAge());
        }
    }


}
