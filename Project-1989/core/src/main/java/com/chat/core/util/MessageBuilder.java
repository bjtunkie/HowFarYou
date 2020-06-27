package com.chat.core.util;

import com.chat.proto.BaseMessage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MessageBuilder {
    private static final LargeInteger requestGen = new LargeInteger(16);

    private final String dstUniqueID;
    private final String sessionID;
    private final String srcUniqueID;

    public MessageBuilder(String srcUniqueID, String dstUniqueID, String sessionID) {
        this.srcUniqueID = srcUniqueID;
        this.dstUniqueID = dstUniqueID;
        this.sessionID = sessionID;
    }

    public BaseMessage.Config getConfigForNextMessage(String responseID) {
        String requestID = requestGen.increment().toString();

        BaseMessage.Config config = new BaseMessage.Config()
                .setDstUniqueID(dstUniqueID)
                .setSrcUniqueID(srcUniqueID)
                .setSessionID(sessionID)
                .setRequestID(requestID)
                .setResponseID(responseID);

        return config;
    }

    public BaseMessage.Config nextConfig() {
        return getConfigForNextMessage("");
    }

    public <T extends BaseMessage> T createResponse(Class<T> tClass, String responseID) {
        T instance = null;
        BaseMessage.Config config = getConfigForNextMessage(responseID);

        try {
            Constructor<T> constructor = tClass.getConstructor(BaseMessage.Config.class);
            instance = constructor.newInstance(config);

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        assert instance != null;
        return instance;
    }

    public <T extends BaseMessage> T createRequest(Class<T> classOfT) {
        return createResponse(classOfT, "");
    }
}
