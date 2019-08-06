package com.ownerkaka.testjdk.mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author akun
 * @since 2019-07-13
 */
@Setter
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private String home_url;
    private String screen_name;
    private String activated;
    private String logged;
    private String group_name;
    private Date created;
    private Date updated;
}