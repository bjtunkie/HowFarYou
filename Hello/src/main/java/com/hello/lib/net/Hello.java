package com.hello.lib.net;

import com.hello.lib.net.impl.ZThread;
import com.hello.lib.net.tool.BaseMessage;
import com.hello.lib.net.tool.MessageResponse;
import com.hello.lib.net.tool.ObjectPool;

public final class Hello {
    private static StagingArea area;

    private Hello() {
    }

    public static <T extends ZThread> void instantiate(boolean startServer, Class<T> threadClass, Object... params) {
        instantiate(startServer, threadClass, null, params);
    }

    public static <T extends ZThread> void instantiate(boolean startServer, Class<T> threadClass, ObjectPool<String, MessageResponse> requestPool, Object... params) {
        synchronized (Hello.class) {
            if (area == null) {
                area = new StagingArea(startServer, threadClass, requestPool, params);
            }
        }
    }


    public static <M extends BaseMessage> void send(M message, MessageResponse response) {
        area.send(message, response);
    }


    public static void makeConnection(String uniqueID, String host, int port) {
        area.getThreadFactory()
                .getThread()
                .makeConnection(uniqueID, host, port);
    }
}

