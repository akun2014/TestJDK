package com.ownerkaka.testjdk.mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author akun
 * @since 2019-07-13
 */
@Setter
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
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