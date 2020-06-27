package org.chat.server.comm;


import com.hello.lib.message.chat.SimpleMessage;
import com.hello.lib.net.ThreadPool;
import com.hello.lib.net.impl.ZThread;
import com.hello.lib.net.tool.BaseMessage;
import com.hello.lib.net.tool.ObjectPool;
import com.hello.lib.net.tool.TCPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CoreThread extends ZThread {
    static final Logger LOGGER = LoggerFactory.getLogger(CoreThread.class);
    private final AuthController authController;
    private final Map<Short, Class<? extends BaseMessage>> map;

    public <T extends ZThread> CoreThread(ThreadPool<T> factory, ObjectPool p, ObjectPool r, Object... params) {
        super(factory, p, r);

        assert params != null;
        assert params[0] != null;

        map = (Map<Short, Class<? extends BaseMessage>>) params[0];
        authController = new AuthController();

    }


    @Override
    protected void onReceive(byte[] input, TCPConnection conn) {
        int code = byteArrayToInt(input, 2);

        Class<? extends BaseMessage> clazz = map.get(code);
        assert clazz != null;

        BaseMessage message = serializer.deSerialize(input, clazz);

        if (code == SimpleMessage.CODE) {
            BaseMessage.Config config = message.getConfigForResponse();
            SimpleMessage reply = new SimpleMessage(config);
            reply.setMessage("Acknowledged...");
            sendMessage(reply, conn);
        }
    }

}
