package com.ownerkaka.testjdk.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-07-13
 */
@Slf4j
@Setter
@Getter
public class User {
    private Long uid;
    private String username;
    private String password;
    private String email;
    private String home_url;
    private String screen_name;
    private String created;
    private String activated;
    private String logged;
    private String group_name;
}