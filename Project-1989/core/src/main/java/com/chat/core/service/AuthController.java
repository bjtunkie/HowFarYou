package com.chat.core.service;

import com.chat.core.persistence.Constant;
import com.chat.core.persistence.UserInfo;
import com.chat.core.util.LargeInteger;
import com.chat.core.util.MessageBuilder;
import com.chat.message.login.SignInRequest;
import com.chat.proto.MessageResponse;
import com.chat.v1.comm.CommChannel;

public class AuthController {

    public String uniqueID;
    public String userID;


    MessageBuilder messageBuilder = new MessageBuilder(Constant.user.uniqueID, com.chat.v1.comm.Constant.MAIN_HOST, LargeInteger.BLANK);

    public void autoLogin() {
        UserInfo user = Constant.user;
        String userID = user.getUserID();
        String uniqueID = user.uniqueID;
        String encryptedPassword = user.encryptedPassword;
    }

    public void login(String userID, String password, MessageResponse response) {
        SignInRequest signInRequest = new SignInRequest(userID, password, messageBuilder.nextConfig());
        CommChannel.send(signInRequest, response);
    }


}
