package com.ownerkaka.testnetty.netty.handler;

import com.alibaba.fastjson.JSON;
import com.ownerkaka.testnetty.netty.pojo.SubscribeReq;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author akun
 * @Date 2021/8/21 20:05
 */
@Slf4j
@ChannelHandler.Sharable
public class SubscribeClientHandler extends ChannelDuplexHandler {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(build());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("{}", JSON.toJSONString(msg));
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("{}", JSON.toJSONString(msg));
        super.write(ctx, msg, promise);
        ctx.writeAndFlush(build());
    }

    private SubscribeReq build() {
        SubscribeReq req = new SubscribeReq();
        req.setSubReqID(100L);
        req.setUserName("akun");
        req.setProductName("计算机");
        req.setPhoneNum("176****0000");
        return req;
    }
}
