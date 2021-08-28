package com.ownerkaka.testcommon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @Author akun
 * @Date 2021/8/24 23:32
 */
@Data
@EqualsAndHashCode(of = {"cardNumber"})
public class User implements Serializable {
    private static final long serialVersionUID = 7102805104793561269L;
    private String name;
    private int age;
    private String address;
    private String phone;
    @NonNull
    private String cardNumber;
}
