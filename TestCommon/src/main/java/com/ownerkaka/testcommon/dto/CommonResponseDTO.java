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
public class CommonResponseDTO implements Serializable {
    private static final long serialVersionUID = 8251655279819418336L;
    private String sequenceId;
    private String data;
    private String desc;
}
