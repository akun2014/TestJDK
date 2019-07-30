package com.ownerkaka.testjdk.support.bean;

import lombok.Data;

/**
 * Created by akun on 2019/3/24.
 */
@Data
public class Human {
    private String gender;
    private String hairColor;
    private String eyeColor;
    private Long created;

    public Human() {
        this.created = System.currentTimeMillis();
    }
}
