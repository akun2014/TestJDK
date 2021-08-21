package com.ownerkaka.testjdk.io.netty.handler;

import com.alibaba.fastjson.JSON;
import com.ownerkaka.testjdk.io.netty.pojo.SubscribeReq;
import com.ownerkaka.testjdk.io.netty.pojo.SubscribeResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author akun
 * @Date 2021/8/21 20:05
 */
@Slf4j
@ChannelHandler.Sharable
public class SubscribeServerHandler extends SimpleChannelInboundHandler<SubscribeReq> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SubscribeReq msg) throws Exception {
        log.info("{}", JSON.toJSONString(msg));
        ctx.writeAndFlush(build(msg.getSubReqID()));
    }

    private SubscribeResp build(Long reqId) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(reqId);
        resp.setRespCode("200");
        resp.setDesc("success");
        return resp;
    }
}
