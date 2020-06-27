package com.chat.desktop.rtchat;

import com.chat.core.util.MessageBuilder;
import com.chat.desktop.service.AbstractFXWindow;
import com.chat.message.chat.SimpleMessage;
import com.chat.proto.BaseMessage;
import com.chat.proto.MessageResponse;
import com.chat.v1.comm.CommChannel;
import com.chat.v1.comm.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SimpleChatScreen extends AbstractFXWindow {
    public static final String RES = "rtchat/SimpleChatScreen.fxml";
    public TextField tf;
    public Button sendBtn;

    public ListView chatPane;
    MessageBuilder messageBuilder;
    String userID;

    public void initialize() {

        userID = "bnairsurya";
        messageBuilder = new MessageBuilder(userID, Constant.MAIN_HOST, "sessionID");


    }

    @Override
    public void work() {

    }

    @FXML
    public void sendBtnAction(ActionEvent actionEvent) {
        String in = tf.getText();
        if (in != null && !in.isBlank()) {
            tf.clear();
            chatPane.getItems().add(in);

            BaseMessage.Config config = messageBuilder.nextConfig();
            SimpleMessage simpleMessage = new SimpleMessage(config);
            simpleMessage.setMessage(in);
            CommChannel.send(simpleMessage, new MessageResponse() {
                @Override
                public <T extends BaseMessage> void onResponse(T r) {
                    SimpleMessage response = (SimpleMessage) r;
                    String reply = response.getSrcUniqueID() + " : " + response.getMessage();
                    synchronized (chatPane) {
                        chatPane.getItems().add(reply);
                    }
                }
            });
        }
    }
}
