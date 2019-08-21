package com.ownerkaka.testjdk.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author akun
 * @since 2019-07-13
 */
@Slf4j
@Getter
@Setter
@ToString
public class Options {
    private String name;
    private String value;
    private String description;
}