package com.gk.proxy.bean;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by akun on 2019/3/3.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Tesla {
    private String name;
    private BigDecimal price;

    public void description() {
        System.out.println(toString());
    }

    public String specialName() {
        return "Model 3";
    }
}
