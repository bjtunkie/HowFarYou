package com.hello.lib.message.login;

import com.hello.lib.net.tool.BaseMessage;

public class SignInResponse extends BaseMessage {
    public SignInResponse(Config config) {
        super(config);
    }

    public String getUniqueID() {
        return getDstUniqueID();
    }
}
