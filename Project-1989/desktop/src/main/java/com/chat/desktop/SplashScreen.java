package com.chat.desktop;

import com.chat.desktop.rtchat.SimpleChatScreen;
import com.chat.desktop.service.AbstractFXWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SplashScreen extends AbstractFXWindow {
    private static final String RES = "splash.fxml";
    @FXML
    private Label label;

    @Override
    public void work() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setScreen(SimpleChatScreen.class);
    }
}
