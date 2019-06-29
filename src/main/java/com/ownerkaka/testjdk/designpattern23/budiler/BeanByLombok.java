package com.ownerkaka.testjdk.designpattern23.budiler;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * @author akun
 * @since 2019-06-28
 */
@Builder
@Getter
class BeanByLombok {
    private String name;
    private Integer num;
    private Date created;
}