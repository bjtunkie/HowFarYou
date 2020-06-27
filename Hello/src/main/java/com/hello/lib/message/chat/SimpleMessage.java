package com.hello.lib.message.chat;

import com.hello.lib.net.tool.BaseMessage;

public class SimpleMessage extends BaseMessage {
    public static final int CODE = 2;
    private String message;

    public SimpleMessage(Config config) {
        super(config);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
