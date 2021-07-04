package com.ownerkaka.testjdk.support.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by akun on 2019/2/27.
 */
@Data
@Component
public class Foo {
    private String type;
    private User user;
    private List<String> member;
    @Autowired
    private Bar bar;


}
