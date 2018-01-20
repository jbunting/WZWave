package com.whizzosoftware.wzwave.controller.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.jsc.JSerialCommChannel;

import java.util.concurrent.locks.LockSupport;

public class BetterJSerialCommChannel extends JSerialCommChannel {
    @Override
    protected int doReadBytes(ByteBuf buf) throws Exception {
        if (!this.isOpen()) {
            return -1;
        }
        if (available() <= 0) {
            // throttle to reduce cpu
            //Thread.sleep(1);
            //Thread.sleep(0, 100_000);
            LockSupport.parkNanos(100_000);
            return 0;
        }
        int read = super.doReadBytes(buf);
        return read;
    }
}
