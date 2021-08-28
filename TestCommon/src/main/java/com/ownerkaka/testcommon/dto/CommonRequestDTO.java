package com.ownerkaka.testcommon.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author akun
 * @Date 2021/8/24 23:56
 */
@Data
@EqualsAndHashCode(of = {"sequenceId"})
public class CommonRequestDTO implements Serializable {
    private static final long serialVersionUID = 4716343888855032843L;
    private String sequenceId;
}
