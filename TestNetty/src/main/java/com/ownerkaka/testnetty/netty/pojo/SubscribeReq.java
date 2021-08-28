package com.ownerkaka.testnetty.netty.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author akun
 * @Date 2021/8/21 19:49
 */
@Data
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 7371688144972991712L;
    private Long subReqID;
    private String userName;
    private String productName;
    private String phoneNum;
    private String address;

}
