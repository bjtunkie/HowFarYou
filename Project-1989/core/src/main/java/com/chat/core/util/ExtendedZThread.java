package com.chat.core.util;

import com.chat.proto.BaseMessage;
import com.chat.proto.MessageResponse;
import com.chat.proto.RequestPool;
import com.chat.v1.comm.ConnectionPool;
import com.chat.v1.comm.TCPConnection;
import com.chat.v1.comm.ThreadPool;
import com.chat.v1.comm.ZThread;

import java.util.Map;

public class ExtendedZThread extends ZThread {

    private final Map<Integer, Class<? extends BaseMessage>> MAP;

    public <T extends ZThread> ExtendedZThread(ThreadPool<T> factory, ConnectionPool p, RequestPool r, Object... params) {
        super(factory, p, r);

        MAP = (Map<Integer, Class<? extends BaseMessage>>) params[0];
    }

    @Override
    protected void onReceive(byte[] in, TCPConnection conn) {

        int typeCode = byteArrayToInt(in, 2);

        Class<? extends BaseMessage> clazz = MAP.get(typeCode);
        assert clazz != null;

        BaseMessage message = serializer.deSerialize(in, clazz);

        String key = message.getResponseID();
        MessageResponse ref = requestPool.extract(key);
        if (ref == null) {

        } else {
            ref.onResponse(message);
        }

    }

}
