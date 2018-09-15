package com.gk;

import com.gk.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by akun on 2018/9/15.
 * 值调用(call by value ) 和 引用调用( call by reference )
 * Java只有值调用
 */
@Slf4j
public class MethodCallByTest {


    private void swap(User user0, User user1) {
        User temp = user0;
        user0 = user1;
        user1 = temp;

        log.info("user0.name:{} ,user1.name:{}", user0.getName(), user1.getName());//print: user0.name:B ,user1.name:A
    }

    @Test
    public void callTest() {
        User user0 = new User();
        user0.setName("A");

        User user1 = new User();
        user1.setName("B");
        swap(user0, user1);

        log.info("user0.name:{} ,user1.name:{}", user0.getName(), user1.getName());// print: user0.name:A ,user1.name:B
    }
}
