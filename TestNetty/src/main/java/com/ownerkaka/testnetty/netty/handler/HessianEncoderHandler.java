package com.ownerkaka.testnetty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.serialize.hessian2.Hessian2ObjectOutput;

import java.io.Serializable;

/**
 * @Author akun
 * @Date 2021/8/24 23:23
 */
@Slf4j
public class HessianEncoderHandler extends MessageToByteEncoder<Serializable> {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    @Override
    protected void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out) throws Exception {
        int startIdx = out.writerIndex();

        ByteBufOutputStream bout = new ByteBufOutputStream(out);
        Hessian2ObjectOutput output = null;
        try {
            bout.write(LENGTH_PLACEHOLDER);
            output = new Hessian2ObjectOutput(bout);
            output.writeObject(msg);
            output.flushBuffer();
        } finally {
            if (output != null) {
                output.cleanup();
            } else {
                bout.close();
            }
        }

        int endIdx = out.writerIndex();

        out.setInt(startIdx, endIdx - startIdx - 4);
    }
}
