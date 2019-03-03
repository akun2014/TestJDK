package com.gk.proxy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by akun on 2019/3/3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyBean {
    private String key;
    private Object value;
}
