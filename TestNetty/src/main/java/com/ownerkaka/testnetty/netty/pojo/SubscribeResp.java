package com.ownerkaka.testnetty.netty.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author akun
 * @Date 2021/8/21 19:49
 */
@Data
public class SubscribeResp implements Serializable {
    private static final long serialVersionUID = 1841113932626107343L;
    private Long subReqID;
    private String respCode;
    private String desc;
}
