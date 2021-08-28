package com.ownerkaka.testnetty.netty.handler;

import com.ownerkaka.testcommon.dto.CommonRequestDTO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.serialize.hessian2.Hessian2ObjectInput;

/**
 * @Author akun
 * @Date 2021/8/24 23:23
 */
@Slf4j
public class HessianDecoderHandler extends LengthFieldBasedFrameDecoder {

    public HessianDecoderHandler(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        Hessian2ObjectInput input = new Hessian2ObjectInput(new ByteBufInputStream(frame, true));
        return input.readObject(CommonRequestDTO.class);
    }
}
